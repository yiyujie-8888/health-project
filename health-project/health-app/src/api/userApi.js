// src/api/userApi.js

/**
 * 登录接口 - /api/user/login
 * @param {Object} params - 登录参数
 * @param {string} params.username - 用户名
 * @param {string} params.password - 密码
 * @returns {Promise} - 返回接口响应数据
 */
export const login = (params) => {
  // 模拟接口响应
  return new Promise((resolve) => {
    const { username, password } = params || {}
    if (username && password) {
      resolve({
        code: 200,
        data: { token: `mock-token-${Date.now()}` }
      })
    } else {
      resolve({
        code: 400,
        msg: '用户名或密码不能为空',
        data: null
      })
    }
  })
}

/**
 * 注册接口
 * @param {Object} params - 注册参数
 * @param {string} params.username - 用户名
 * @param {string} params.password - 密码
 * @param {string} params.phone - 手机号（示例）
 * @returns {Promise} - 返回接口响应数据
 */
export const register = (params) => {
  // 模拟接口响应
  return new Promise((resolve) => {
    resolve({
      code: 200,
      msg: '注册成功',
      data: null
    })
  })
}

/**
 * 用户信息接口 - /api/user/info
 * @returns {Promise} - 返回接口响应数据
 */
export const getUserInfo = () => {
  // 模拟接口响应
  return new Promise((resolve) => {
    resolve({
      code: 200,
      data: {
        id: 1,
        username: 'admin',
        name: '管理员',
        avatar: 'https://example.com/avatar.jpg',
        roles: ['admin'],
        permissions: ['*']
      }
    })
  })
}

// 导出所有接口函数（也可按需导出）
export default {
  login,
  register,
  getUserInfo
}