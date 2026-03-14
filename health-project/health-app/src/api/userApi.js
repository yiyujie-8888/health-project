// src/api/userApi.js
import request from '../utils/request' // 引入封装后的axios实例

/**
 * 登录接口
 * @param {Object} params - 登录参数
 * @param {string} params.username - 用户名
 * @param {string} params.password - 密码
 * @returns {Promise} - 返回接口响应数据
 */
export const login = (params) => {
  return request({
    url: '/user/login', // 后端登录接口的路径（拼接baseURL后为完整地址）
    method: 'post', // 登录接口通常为POST
    data: params // 传递用户名/密码参数
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
  return request({
    url: '/user/register',
    method: 'post',
    data: params
  })
}

// 导出所有接口函数（也可按需导出）
export default {
  login,
  register
}