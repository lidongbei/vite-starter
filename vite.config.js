import { defineConfig } from 'vite'
import path from 'path'

import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'

import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite';
import { AntDesignVueResolver, ElementPlusResolver } from 'unplugin-vue-components/resolvers';

// import Icons from 'unplugin-icons/vite'
import IconsResolver from 'unplugin-icons/resolver'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    vueJsx({}),
    AutoImport({
      resolvers: [
        // IconsResolver({
        //   prefix: 'Icon',
        // }),
        ElementPlusResolver()
      ],
      imports: ['vue', 'vue-router', 'pinia'],
      // dts: 'src/auto-import.d.ts'
    }),
    Components({
      resolvers: [
        // IconsResolver({
        //   enabledCollections: [],
        // }),

        AntDesignVueResolver({ importStyle: false, resolveIcons: true }),
        ElementPlusResolver(),
      ],
    }),
    // Icons({
    //   autoInstall: true,
    //   compiler: 'vue3',
    // }),
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
