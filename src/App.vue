<script setup>
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
// import dayjs from 'dayjs';
// import 'dayjs/locale/zh-cn';
// dayjs.locale('cn');

</script>

<template>
  <el-config-provider :locale="zhCn">
    <router-view></router-view>
  </el-config-provider>
</template>

<style scoped>

</style>
