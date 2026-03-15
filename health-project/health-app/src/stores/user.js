import { defineStore } from 'pinia'

export const useUserStore = defineStore('user', {
  state: () => ({
    token: '',
    platformType: '' // 存储当前端类型，如 'pc' / 'mobile' / 'pad'
  }),
  actions: {
    // 存储 token
    setToken(token) {
      this.token = token
    },
    // 存储端类型
    setPlatformType(type) {
      this.platformType = type
    },
    // 清空状态（如登出）
    clearUserInfo() {
      this.token = ''
      this.platformType = ''
    }
  }
})