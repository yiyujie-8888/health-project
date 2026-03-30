import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../Pages/HomeView.vue'
import LoginView from '../Pages/LoginView.vue'
import PlatformSelectView from '../Pages/PlatformSelectView.vue'
import startView from '../Pages/startView.vue'
import forgetView from '../Pages/forgetView.vue'
import oldView from '../Pages/oldView.vue'
import sonView from '../Pages/sonView.vue'
import youngView from '../Pages/youngView.vue'
const routes = [
  {
    path: '/',
    name: 'start',
    component: startView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/forget',
    name: 'forget',
    component: forgetView
  },
  {
    path: '/select',
    name: 'platform-select',
    component: PlatformSelectView
  },
  {
    path: '/old',
    name: 'old',
    component: oldView
  },
  {
    path: '/son',
    name: 'son',
    component: sonView
  },
  {
    path: '/young',
    name: 'young',
    component: youngView
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router