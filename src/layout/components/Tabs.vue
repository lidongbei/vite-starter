<template>
  <a-tabs
    v-model:activeKey="activeKey"
    :tab-position="mode"
    :style="{ height: '200px' }"
    @tabScroll="callback"
  >
    <a-tab-pane
      v-for="i in 30"
      :key="i"
      :tab="`Tab-${i}`"
    >Content of tab {{ i }}</a-tab-pane>
  </a-tabs>
</template>

<style scoped>

</style>
