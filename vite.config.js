import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import path from 'path'

import Components from 'unplugin-vue-components/vite';
import { AntDesignVueResolver } from 'unplugin-vue-components/resolvers';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    vueJsx({}),
    Components({
      resolvers: [
        AntDesignVueResolver({ importStyle: false, resolveIcons: true }),
      ],
    }),
  ],
  server: {
    proxy: {
        // 选项写法
      '/api': {
        // 所要代理的目标地址
        target: 'http://localhost:8080', 
        // 重写要代理到远程服务器的path,比如在本地项目, baseUrl是 `/api/xxx`, 被重写之后就变成 `/xxx`,被重写之后的path也正是我们要代理到远程的接口路径
        rewrite: path => path.replace(/^\/api/, ''), 
        changeOrigin: true,  // true/false, Default: false - changes the origin of the host header to the target URL
      } 
    }
  },
  resolve: {
    //配置路径别名
    alias: {
      '@': path.resolve(__dirname, './src')
    },
  },
})
