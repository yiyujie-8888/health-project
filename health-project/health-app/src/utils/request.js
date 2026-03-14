// src/utils/request.js
import axios from 'axios'
import { useUserStore } from '../stores/user' // 引入pinia的userStore
import { ElMessage } from 'element-plus' // 可选：推荐用element-plus的提示组件，也可替换为alert

// 1. 创建axios实例并配置基础参数
const service = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || '/api', // 基础请求地址（推荐从环境变量读取）
  timeout: 10000, // 超时时间：10秒
  headers: {
    'Content-Type': 'application/json;charset=utf-8' // 默认请求头
  }
})

// 2. 请求拦截器：自动携带token
service.interceptors.request.use(
  (config) => {
    // 获取pinia中的token
    const userStore = useUserStore()
    if (userStore.token) {
      // 给请求头添加Authorization：Bearer + token
      config.headers.Authorization = `Bearer ${userStore.token}`
    }
    return config
  },
  (error) => {
    // 请求错误（如参数错误、网络未连接）
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

    switch (status) {
      case 401:
        msg = '登录状态过期，请重新登录！'
        // 401时清空用户信息并跳转登录页
        const userStore = useUserStore()
        userStore.clearUserInfo()
        window.location.href = '/login' // 或用vue-router跳转：router.push('/login')
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

    // 弹出错误提示
    ElMessage.error(msg)
    return Promise.reject(error) // 抛出错误，让业务层可捕获
  }
)

// 导出axios实例
export default service