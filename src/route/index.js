import { createRouter, createWebHashHistory } from 'vue-router'

// 具名同步导入
let routeConfigs = import.meta.glob(['@/views/**/route.js'], { import: 'default', eager: true })

const autoRoutes = []
const inLayoutRoutes = []

const defaultMate = {
  transition: 'slide-left'
}

/**
 * 处理非 / 开头的 path 加入到 layout 中 path开头的路由使用为主页面 不在 layout 中
 */
for (const key in routeConfigs) {
  const routeArr = routeConfigs[key]
  for (const route of routeArr) {
    route.meta = { ...defaultMate, ...route.meta }
    if (route.path.startsWith('/')) {
      autoRoutes.push(route)
    } else {
      inLayoutRoutes.push(route)
    }
  }
}

// console.log(inLayoutRoutes)

const routes = [
  {
    path: '/',
    component: () => import('@/layout/index.vue'),
    meta: { transition: 'slide-left' },
    children: inLayoutRoutes
  },
  {
    path: '/a',
    component: () => import('@/views/testJsx.jsx'),
    meta: { transition: 'slide-left' },
  },
  ...autoRoutes
]


const router = createRouter({
  // 内部提供了 history 模式的实现。为了简单起见，我们在这里使用 hash 模式。
  history: createWebHashHistory(),

  routes,
})


export default router
