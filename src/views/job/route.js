export default [
  {
    path: 'mock',
    component: () => import('./index.vue'),
    meta: { transition: 'slide-right' },
  }
]
