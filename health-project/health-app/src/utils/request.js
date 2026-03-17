// src/utils/request.js
import axios from 'axios'
import { useUserStore } from '../stores/user' // 引入pinia的userStore
import { ElMessage } from 'element-plus' // 可选：推荐用element-plus的提示组件，也可替换为alert

// 1. 创建axios实例并配置基础参数
const service = axios.create({
  //baseURL: import.meta.env.VITE_API_BASE_URL || '/api', // 基础请求地址（推荐从环境变量读取）
  baseURL: '',
  timeout: 10000, // 超时时间：10秒
  headers: {
    'Content-Type': 'application/json;charset=utf-8' // 默认请求头
  }
})

// 2. 请求拦截器：自动携带token
service.interceptors.request.use(
  (config) => {
    // 确保 config 存在
    if (!config) {
      return config
    }
    
    // 模拟接口响应
    if (config.url === '/user/login' && config.method === 'post') {
      const { username, password } = config.data || {}
      if (username && password) {
        return Promise.resolve({
          data: {
            code: 200,
            msg: '登录成功',
            data: { token: `mock-token-${Date.now()}` }
          }
        })
      } else {
        return Promise.resolve({
          data: {
            code: 400,
            msg: '用户名或密码不能为空',
            data: null
          }
        })
      }
    }
    
    if (config.url === '/user/info' && config.method === 'get') {
      return Promise.resolve({
        data: {
          code: 200,
          msg: '获取用户信息成功',
          data: {
            id: 1,
            username: 'admin',
            name: '管理员',
            avatar: 'https://example.com/avatar.jpg',
            roles: ['admin'],
            permissions: ['*']
          }
        }
      })
    }
    
    // 获取pinia中的token
    try {
      const userStore = useUserStore()
      if (userStore.token) {
        // 给请求头添加Authorization：Bearer + token
        config.headers.Authorization = `Bearer ${userStore.token}`
      }
    } catch (error) {
      console.error('获取用户信息失败:', error)
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

    try {
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