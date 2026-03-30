import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { viteMockServe } from 'vite-plugin-mock'
import path from 'path'
import { fileURLToPath } from 'url'

const __dirname = path.dirname(fileURLToPath(import.meta.url))

export default defineConfig({
  plugins: [vue(), viteMockServe({
    mockPath: path.resolve(__dirname, './mock'),
    localEnabled: true,
    supportTs: false,
    logger: false
  })],
  // 路径别名配置（关键：让 @ 指向 src）
  resolve: {
    alias: {
      '@': path.resolve(__dirname, './src')
    }
  }
})