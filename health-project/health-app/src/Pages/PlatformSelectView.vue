<template>
  <div>
    <h1>选择端类型</h1>
    <button @click="selectPlatform('old')">老人</button>
    <button @click="selectPlatform('son')">子女</button>
	<button @click="selectPlatform('young')">年轻人</button>
    <p>当前选择的端类型：{{ userStore.platformType }}</p>
  </div>
</template>

<script setup>
import { useUserStore } from '../stores/user'
const userStore = useUserStore()

const selectPlatform = (type) => {
  userStore.setPlatformType(type)
}
</script>