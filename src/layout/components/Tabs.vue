<script setup>
import { configProviderKey } from 'ant-design-vue/es/config-provider/context'

const config = inject(configProviderKey)

console.log(config.theme.value)

const mode = ref('top');

const tabs = ref([
  { tab: '标签名1标签名1', key: 1, closable: false },
  { tab: '标签名签名2', key: 2, closable: true },
  { tab: '标签名签名3', key: 3, closable: true },
  { tab: '标签名签名4', key: 4, closable: true },
  { tab: '标签名签名5', key: 5, closable: true },
  { tab: '标签名签名6', key: 6, closable: true },
  { tab: '标签名签名7', key: 7, closable: true },
  { tab: '标签名签名8', key: 8, closable: true },
  { tab: '标签名签名9', key: 9, closable: true },
  { tab: '标签名签名10', key: 10, closable: true },
  { tab: '标签名签名11', key: 11, closable: true },
  { tab: '标签名签名12', key: 12, closable: true },
])

const activeKey = ref(1);

const callback = val => {
  console.log(val);
};

const tabsEdit = (key, action) => {
  if (action === 'remove') {
    tabs.value = tabs.value.filter(item => item.key !== key)
  }
}
</script>

<template>
  <a-tabs
    v-model:activeKey="activeKey"
    :tab-position="mode"
    :style="{ height: '20px' }"
    :hide-add="true"
    :tab-bar-style="{ 'border-radius': 0, }"
    type="editable-card"
    @tabScroll="callback"
    @edit="tabsEdit"
  >
    <!-- <template #renderTabBar="{ DefaultTabBar, ...props }">
      <component :is="DefaultTabBar" v-bind="props"></component>
    </template> -->
    <a-tab-pane
      v-for="tab in tabs"
      v-bind="tab"
    >
    </a-tab-pane>
  </a-tabs>
</template>

<style
  lang="scss"
  scoped
>
.ant-tabs-card {
  &:deep(.ant-tabs-tab) {
    padding: 4px 8px;
  }

  :deep(.ant-tabs-tab-remove) {
    margin-left: 0;
    font-size: 10px;
  }

  :deep(.ant-tabs-nav-more) {
    padding: 4px 8px;
  }
}

.ant-tabs-dropdown {
  :deep(.ant-tabs-dropdown-menu-item) {
    padding: 4px 8px;
  }

  .ant-tabs-dropdown-menu-item-remove {
    margin-left: 0;
    font-size: 10px;
  }
}
</style>
