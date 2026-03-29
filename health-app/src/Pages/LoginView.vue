<template>
  <div class="login-container">
    <!-- 登录/注册标签切换 -->
    <div class="tab-group">
      <button 
        class="tab-btn" 
        :class="{ active: isLoginTab }" 
        @click="switchTab(true)"
      >
        登录
      </button>
      <button 
        class="tab-btn" 
        :class="{ active: !isLoginTab }" 
        @click="switchTab(false)"
      >
        注册
      </button>
    </div>

    <!-- 登录表单 -->
    <div v-if="isLoginTab" class="form-content">
      <h1>登录页</h1>
      <!-- 用户名输入框 -->
      <div class="input-item">
        <input v-model="loginForm.username" placeholder="请输入用户名" type="text" />
      </div>
      <!-- 密码输入框 -->
      <div class="input-item">
        <input v-model="loginForm.password" placeholder="请输入密码" type="password" />
      </div>
      <!-- 登录按钮 -->
      <button @click="handleLogin">登录</button>
    </div>

    <!-- 注册表单 -->
    <div v-else class="form-content">
      <h1>注册页</h1>
      <!-- 用户名 -->
      <div class="input-item">
        <input v-model="registerForm.username" placeholder="请输入用户名" type="text" />
      </div>
      <!-- 密码 -->
      <div class="input-item">
        <input v-model="registerForm.password" placeholder="请输入密码" type="password" />
      </div>
      <!-- 手机号（可选，根据需求调整） -->
      <div class="input-item">
        <input v-model="registerForm.phone" placeholder="请输入手机号" type="tel" />
      </div>
      <!-- 注册按钮 -->
      <button @click="handleRegister">注册</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '../stores/user'
import { getUserInfo } from '../api/userApi'
import request from '@/utils/request'
import { ElMessage } from 'element-plus' // 引入提示组件

const router = useRouter()
const userStore = useUserStore()

// 切换登录/注册标签
const isLoginTab = ref(true)
const switchTab = (isLogin) => {
  isLoginTab.value = isLogin
}

// 登录表单数据
const loginForm = ref({
  username: '',
  password: ''
})

// 注册表单数据
const registerForm = ref({
  username: '',
  password: '',
  phone: '' // 可根据需求添加更多字段
})

const handleLogin = async () => {
  try {  
	// 校验用户名/密码
	if (!loginForm.value.username || !loginForm.value.password) {
	    ElMessage.warning('用户名或密码不能为空！')
	    return
	}
    // 1. 调用登录接口
	const res = await request.post('/api/user/login', {
         username: loginForm.value.username,
         password: loginForm.value.password
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
// 注册逻辑
const handleRegister = async () => {
  try {
    // 表单校验
    if (!registerForm.value.username) {
      ElMessage.warning('请输入用户名！')
      return
    }
    if (!registerForm.value.password) {
      ElMessage.warning('请输入密码！')
      return
    }
    if (!registerForm.value.phone || !/^1[3-9]\d{9}$/.test(registerForm.value.phone)) {
      ElMessage.warning('请输入有效的手机号！')
      return
    }

    // 核心：注册成功后直接生成token（和登录接口token格式一致）
    const mockToken = `mock-token-${Date.now()}`
    userStore.setToken(mockToken) // 存储token到pinia
    
    // 注册成功后调用用户信息接口（复用登录态逻辑）
    const userInfoRes = await getUserInfo()
    if (userInfoRes.code === 200) {
      ElMessage.success('注册成功！已自动获取用户信息')
      console.log('注册后获取的用户信息：', userInfoRes.data)
    }

    // 直接跳转到三端选择页
    router.push('/select')
    // 清空注册表单
    registerForm.value = { username: '', password: '', phone: '' }
    
  } catch (error) {
    console.error('注册失败：', error)
    ElMessage.error('注册异常，请稍后重试！')
	// 异常时清空token（避免无效登录态）
    userStore.clearUserInfo()
  }
}
</script>
<style scoped>
/* 新增样式：标签切换 + 表单布局 */
.login-container {
  width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #e5e7eb;
  border-radius: 8px;
}

.tab-group {
  display: flex;
  margin-bottom: 20px;
}

.tab-btn {
  flex: 1;
  padding: 10px;
  border: none;
  background: #f3f4f6;
  cursor: pointer;
}

.tab-btn.active {
  background: #646cff;
  color: white;
}

.input-item {
  margin-bottom: 16px;
}

.input-item input {
  width: 100%;
  padding: 10px;
  border: 1px solid #d1d5db;
  border-radius: 4px;
  box-sizing: border-box;
}

.form-content button {
  width: 100%;
  margin-top: 8px;
}
</style>