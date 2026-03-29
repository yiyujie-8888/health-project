import { defineStore } from 'pinia'
import { ref } from 'vue'
import { getUserInfo, logout } from '../api/userApi'
import { ElMessage } from 'element-plus'
export const useUserStore = defineStore('user', {
  state: () => ({
    token: localStorage.getItem('userToken') || '',
    platformType: '' ,// 存储当前端类型
	userInfo: {} // 新增：存储用户信息（用户名、手机号、密码等）
  }),
  actions: {
    // 存储 token
    setToken(token) {
      this.token = token
	  localStorage.setItem('userToken', token)
    },
    // 存储端类型
    setPlatformType(type) {
      this.platformType = type
    },
    // 清空状态（如登出）
    clearUserInfo() {
      this.token = ''
      this.platformType = ''
	  this.userInfo = {} // 清空用户信息
    },
	// 4. 新增：获取当前登录用户信息（核心方法）
    async fetchUserInfo() {
      // 无token时直接返回，避免无效请求
      if (!this.token) {
        ElMessage.warning('未检测到登录状态，请先登录')
        return
      }

      try {
        // 调用用户信息接口
        const res = await getUserInfo()
        if (res.code === 200) {
          // 存储用户信息到state
          this.userInfo = res.data
        } else {
          // token失效/接口异常：清空状态并提示
          this.clearUserInfo()
          ElMessage.error(res.msg || '登录状态失效，请重新登录')
          // 可选：跳转登录页（需先导入router，或在页面中处理）
          // router.push('/login')
        }
      } catch (error) {
        console.error('获取用户信息失败：', error)
        ElMessage.error('获取用户信息异常，请稍后重试')
      }
    },

  }
})