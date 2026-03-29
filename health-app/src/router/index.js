import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../Pages/HomeView.vue'
import LoginView from '../Pages/LoginView.vue'
import PlatformSelectView from '../Pages/PlatformSelectView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/select',
    name: 'platform-select',
    component: PlatformSelectView
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router