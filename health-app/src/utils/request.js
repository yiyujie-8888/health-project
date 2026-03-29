// src/utils/request.js
import axios from 'axios'
import { useUserStore } from '../stores/user' // 引入pinia的userStore
import { ElMessage } from 'element-plus' // 可选：推荐用element-plus的提示组件，也可替换为alert
import router from '../router'

// 1. 创建axios实例并配置基础参数
const service = axios.create({
  baseURL: '', // 基础请求地址
  timeout: 10000, // 超时时间：10秒
  headers: {
    'Content-Type': 'application/json;charset=utf-8' // 默认请求头
  }
})

// 2. 请求拦截器：自动携带token
service.interceptors.request.use(
  (config) => {
    // 保护headers，避免undefined
        config.headers = config.headers || {}
        
        // 从localStorage获取token
        try {
          const token = localStorage.getItem('userToken')
          if (token) {
            config.headers.Authorization = `Bearer ${token}`
          }
        } catch (error) {
          console.error('获取token失败:', error)
        }
    
        // 【修复核心】DEV环境模拟响应：改用响应拦截器处理，而非请求拦截器
        // 请求拦截器只返回config，不修改响应逻辑
        return config
      },
      (error) => {
        ElMessage.error('请求参数错误或网络异常，请检查！')
        return Promise.reject(error)
      }
    )

// 3. 响应拦截器：统一处理错误
service.interceptors.response.use(
  (response) => {
    // 响应成功：直接返回响应数据（简化业务层调用）
    return response.data
  },
  (error) => {
    // 响应错误：统一处理401/403/500等状态码
    const status = error.response?.status || 0
    let msg = '服务器响应异常，请稍后重试！'

    try {
      switch (status) {
        case 401:
          msg = '登录状态过期，请重新登录！'
          // 401时清空用户信息并跳转登录页
          localStorage.removeItem('userToken')
          localStorage.removeItem('userInfo')
          router.push('/login')
          break
        case 403:
          msg = '暂无权限访问该资源！'
          break
        case 500:
          msg = '服务器内部错误，请联系管理员！'
          break
        case 404:
          msg = '请求接口不存在！'
          break
        default:
          msg = error.message || msg
      }
    } catch (error) {
      console.error('处理响应错误失败:', error)
    }

    // 弹出错误提示
    ElMessage.error(msg)
    return Promise.reject(error) // 抛出错误，让业务层可捕获
  }
)

// 导出axios实例
export default service