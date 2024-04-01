<script
  lang="ts"
  setup
>
import { ref } from 'vue';
import Menu from './components/Menu.vue';

const selectedKeys = ref<string[]>(['1']);
const collapsed = ref<boolean>(false);
</script>

<template>
  <a-layout class="content">
    <a-layout-sider
      v-model:collapsed="collapsed"
      :trigger="null"
      collapsedWidth="64px"
      collapsible
      class="content"
    >
      <div class="logo" />
      <Menu></Menu>
    </a-layout-sider>
    <a-layout class="content">
      <a-layout-header style="background: #fff; padding: 0">
        <menu-unfold-outlined
          v-if="collapsed"
          class="trigger"
          @click="() => (collapsed = !collapsed)"
        />
        <menu-fold-outlined
          v-else
          class="trigger"
          @click="() => (collapsed = !collapsed)"
        />
      </a-layout-header>
      <a-layout-content>
        Content
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<style scoped>
.ant-layout {
  min-height: 100vh;

}

.trigger {
  font-size: 18px;
  line-height: 64px;
  padding: 0 24px;
  cursor: pointer;
  transition: color 0.3s;
}

.trigger:hover {
  color: #1890ff;
}

.logo {
  height: 32px;
  background: rgba(255, 255, 255, 0.3);
  margin: 16px;
}

.site-layout .site-layout-background {
  background: #fff;
}
</style>
