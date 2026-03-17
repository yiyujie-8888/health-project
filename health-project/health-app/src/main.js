// src/main.js
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import ElementPlus from 'element-plus' // 新增
import 'element-plus/dist/index.css' // 新增
import App from './App.vue'
import router from './router'
// 导入 Mock 配置（开发环境才导入，生产环境注释掉）
import './mock/index.js'

const app = createApp(App)
app.use(createPinia())
app.use(router)
app.use(ElementPlus) // 新增：注册element-plus
app.mount('#app')