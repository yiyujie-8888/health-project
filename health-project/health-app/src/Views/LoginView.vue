<template>
  <div>
    <h1>登录页</h1>
    <button @click="handleLogin">登录</button>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
const router = useRouter()

const handleLogin = () => {
  // 模拟登录成功后跳转
  router.push('/select')
}
</script>