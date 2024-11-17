
let modules = import.meta.glob(['@/views/**/*.vue', '!@/views/**/components/**/*.vue'])

const components = {}

for (const key in modules) {
  let path = key.replace('.vue', '')
  if (path.startsWith('/src/views')) {
    path = path.substring('/src/views'.length)
  }
  if (path.endsWith('/index')) {
    path = path.substring(0, path.length - 6)
  }
  // 校验路由冲突
  if (components.hasOwnProperty(path)) {
    const component = components[path]
    console.warn(`组件: ${component.originalPath} 与 ${key} 冲突`)
  }

  components[path] = {
    originalPath: key,
    source: modules[key],
  }
}

console.log(components)

for (const key in components) {
  let title = key.startsWith('/') ? key.substring(1) : key
  title = 'route.title.' + title.replace(/\//g, '.')
  console.log(title)
  autoRoutes.push({
    path: key,
    component: components[key].source,
    meta: { title, transition: 'slide-left' },
  })
}
