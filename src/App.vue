<script setup>
import { ref } from 'vue'
// import 'ant-design-vue/dist/reset.css';
// import 'ant-design-vue/es/message/style/css';
// import enUS from 'ant-design-vue/es/locale/en_US';
import zhCN from 'ant-design-vue/es/locale/zh_CN';
import dayjs from 'dayjs';
import 'dayjs/locale/zh-cn';
dayjs.locale('cn');

import { storeToRefs } from 'pinia'
import { useLoadingStore } from '@/store/loading'
const loadingStore = useLoadingStore()

const { isLoading, message } = storeToRefs(loadingStore)

const theme = ref({
  token: {
    // colorPrimary: "#A0D911",
    // colorSuccess: "#2F54EB",
    // colorError: "#F5222D",
    // colorInfo: "#A0D911",
    // wireframe: true,
    borderRadius: 2,
    // fontSize: 14,
    // sizeStep: 4,
    // sizeUnit: 4
  }
})

</script>

<template>
  <a-config-provider :locale="zhCN" :theme="theme">
    <a-app>
      <a-spin :tip="message" :spinning="isLoading">
        <router-view></router-view>
      </a-spin>
    </a-app>
  </a-config-provider>
</template>

<style scoped>

</style>
