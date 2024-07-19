export default [
  {
    path: '/animation',
    component: () => import('./index.vue'),
    meta: { transition: 'slide-left' },
  }
]
