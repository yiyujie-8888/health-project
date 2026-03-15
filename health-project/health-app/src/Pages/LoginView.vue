<template>
  <div>
    <h1>登录页</h1>
    <!-- 新增：用户名/密码输入框 -->
        <div>
          <input v-model="username" placeholder="请输入用户名" />
          <input v-model="password" type="password" placeholder="请输入密码" />
        </div>
        <button @click="handleLogin">登录</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '../stores/user'
import { login } from '../api/userApi' // 引入登录接口函数

const router = useRouter()
const userStore = useUserStore()

// 新增：绑定输入框的响应式变量
const username = ref('')
const password = ref('')

const handleLogin = async () => {
  try {
	// 校验输入（可选，避免空参数调用）
    if (!username.value || !password.value) {
      console.log('用户名/密码不能为空！')
      return
    }	  
    // 1. 调用登录接口
    const res = await login({
      username: username.value,
      password: password.value
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