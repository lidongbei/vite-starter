<script setup>
// import {
//   Check,
//   Delete,
//   Edit,
//   Message,
//   Search,
//   Star,
// } from '@element-plus/icons-vue'

import { ref } from "vue"
import { useColorStore } from "@/store/color";
// ...
const themeName = ref('默认主题');
const colorStore = useColorStore();
// ...
// let _theme = [
//   {label:'默认主题', color:[["primary", "#409EFF"], ["success", "#67C23A"], ["warning", "#E6A23C"], ["danger", "#F56C6C"], ["error", "#F56C6C"], ["info", "#909399"]]},
//   {label:'自定义主题1', color:[["primary", "#1984f3"], ["success", "#55DE12"], ["warning", "#EA9412"], ["danger", "#E12020"], ["error", "#E12020"], ["info", "#209399"]]},
//   {label:'自定义主题2', color:[["primary", "#0A4680"], ["success", "#276409"], ["warning", "#815410"], ["danger", "#931d1d"], ["error", "#931D1D"], ["info", "#454A55"]]},
// ];

let _theme = [
  // {label:'默认主题', color:[["primary", "#409EFF"], ["success", "#67C23A"], ["warning", "#E6A23C"], ["danger", "#F56C6C"], ["error", "#F56C6C"], ["info", "#909399"]]},
  {
    label: '默认主题', color: {
      "primary": "#409EFF",
      "success": "#67C23A",
      "warning": "#E6A23C",
      "danger": "#F56C6C",
      "error": "#F56C6C",
      "info": "#909399",
    }
  },
  {
    label: '自定义主题1', color: {
      "primary": "#1984f3",
      "success": "#55DE12",
      "warning": "#EA9412",
      "danger": "#E12020",
      "error": "#E12020",
      "info": "#209399",
    }
  }
  // {label:'自定义主题1', color:[["primary", "#1984f3"], ["success", "#55DE12"], ["warning", "#EA9412"], ["danger", "#E12020"], ["error", "#E12020"], ["info", "#209399"]]},
  // {label:'自定义主题2', color:[["primary", "#0A4680"], ["success", "#276409"], ["warning", "#815410"], ["danger", "#931d1d"], ["error", "#931D1D"], ["info", "#454A55"]]},
];

// ...
const setThemeColor = (colorMap, label) => {
  themeName.value = label;
  colorStore.setThemeColor(colorMap);
}
// ...
// ElLoading.service({})
const open2 = () => {
  ElMessage({
    message: 'Congrats, this is a success message.',
    type: 'success',
  })
}

const value2 = ref()

</script>

<template>
  <el-container>
    <el-aside width="200px">Aside</el-aside>
    <el-container>
      <el-header>Header</el-header>
      <el-main>
        <el-row style="margin-bottom: 15px">
          <div class="demo-color-warp">
            <!-- <div v-for="item in _theme"> {{ item }} </div> -->
            <div class="demo-color-box" v-for="(item, key) in _theme" :key="key"
              @click="setThemeColor(item.color, item.label)" :style="{ '--color': item.color.primary }">
              <!-- {{  item  }} -->
              <span class="demo-color__label">{{ item.label }}</span>
              <!-- <span class="demo-color__value">{{ item.color[0][0] }}</span> -->
              <!-- <span class="demo-color__value">{{ item.color[0][1] }}</span> -->
              <!-- <ul class="demo-color__list">
                <template v-for="(colorItem, colorKey) in item.color" :key="colorKey">
                  <li v-if="colorKey>0" :style="{'--color':colorItem[1]}">
                  </li>
                </template>
</ul> -->
            </div>
          </div>
          <span>当前主题：{{ themeName }}</span>
          <el-date-picker
            v-model="value2"
            type="date"
            placeholder="Pick a day"
          />
        </el-row>

        <div class="mb-4">
          <el-button>Default</el-button>
          <el-button type="primary">Primary</el-button>
          <el-button @click="open2" type="success">Success</el-button>
          <el-button type="info">Info</el-button>
          <el-button type="warning">Warning</el-button>
          <el-button type="danger">Danger</el-button>
        </div>

        <div class="mb-4">
          <el-button plain>Plain</el-button>
          <el-button type="primary" plain>Primary</el-button>
          <el-button type="success" plain>Success</el-button>
          <el-button type="info" plain>Info</el-button>
          <el-button type="warning" plain>Warning</el-button>
          <el-button type="danger" plain>Danger</el-button>
        </div>

        <div class="mb-4">
          <el-button round>Round</el-button>
          <el-button type="primary" round>Primary</el-button>
          <el-button type="success" round>Success</el-button>
          <el-button type="info" round>Info</el-button>
          <el-button type="warning" round>Warning</el-button>
          <el-button type="danger" round>Danger</el-button>
        </div>

        <div>
          <el-button circle >
            <el-icon><Search /></el-icon>
          </el-button>
          <el-button type="primary" icon="Edit" circle />
          <!-- <el-button type="success" :icon="Check" circle />
          <el-button type="info" :icon="Message" circle />
          <el-button type="warning" :icon="Star" circle />
          <el-button type="danger" :icon="Delete" circle /> -->
        </div>
      </el-main>
      <el-footer>Footer</el-footer>
    </el-container>
  </el-container>
</template>

<style scoped lang="scss"></style>
