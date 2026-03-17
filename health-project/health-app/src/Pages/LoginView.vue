<template>
  <div class="login-container">
    <h1>登录页</h1>
    <!-- 用户名输入框 -->
    <div class="input-item">
      <input v-model="username" placeholder="请输入用户名" type="text" />
    </div>
    <!-- 密码输入框 -->
    <div class="input-item">
      <input v-model="password" placeholder="请输入密码" type="password" />
    </div>
    <!-- 登录按钮 -->
    <button @click="handleLogin">登录</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '../stores/user'
import { login } from '../api/userApi' // 引入登录接口函数
import request from '@/utils/request'

const router = useRouter()
const userStore = useUserStore()

// 新增：绑定输入框的响应式变量
const username = ref('')
const password = ref('')

const handleLogin = async () => {
  try {  
    // 1. 调用登录接口
	const res = await request.post('/api/user/login', {
         username: 'admin',
         password: '123456'
		})
    // 2. 打印返回结果
    console.log('登录接口返回结果：', res)
    
    // 3. 登录成功：存储token（示例，需根据后端返回的token字段调整）
    if (res.code === 200) {
      userStore.setToken(res.data.token) // 假设后端返回token在res.data.token
      router.push('/select')
    }
  } catch (error) {
    // 捕获接口调用异常（如网络错误、401等）
    console.error('登录失败：', error)
  }
}
</script>