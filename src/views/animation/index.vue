<template>
  <div class="list">
    <div class="cc rowup">
      <div class="item" v-for="row in rows">{{row.key}} - {{ row.title }}</div>
      <div class="item" v-for="row in rows">{{row.key}} - {{ row.title }}</div>
    </div>
  </div>
  <div><el-button type="primary" @click="resetData">更新数据</el-button></div>
</template>

<script setup>
import { onMounted } from 'vue';


const rows = ref([])

let key = 1

const resetData = () => {
  const newRows = []
  for (let i = 1; i < 10; i++, key++) {
    let str
		//汉字对应的unicode编码为u4e00-u9fa5转为10进制为19968-40869，先取随机数，再转为16进制
    str = '\\u' + (Math.floor(Math.random() * (40869 - 19968)) + 19968).toString(16)
		//在用正则操作数据后进行解码。注意：unescape() 函数在 JavaScript 1.5 版中已弃用。请使用 decodeURI() 或 decodeURIComponent() 代替。
    str = decodeURI(str);
    newRows.push({
      key: key,
      title: str
    })
  }

  rows.value = newRows
}

onMounted(()=> {
  resetData()
})

</script>

<style lang="scss" scoped>
@keyframes rowup {
  0% {
    // -webkit-transform: translate3d(0, 0, 0);
    transform: translate3d(0, 0, 0);
  }

  100% {
    // -webkit-transform: translate3d(0, -307px, 0);
    transform: translate3d(0, -50%, 0);
    display: none;
  }
}

.list {
  width: 300px;
  border: 1px solid #999;
  margin: 20px auto;
  position: relative;
  height: 200px;
  overflow: hidden;

}

.list .rowup {
  // -webkit-animation: 10s rowup linear infinite normal;
  animation: 5s rowup linear infinite normal;
  position: relative;

  &:hover {
    /* 控制动画执行状态 paused：暂停 running: 播放 */
    animation-play-state: paused;
  }
}

.item {
  border: 1px solid red;
}
</style>
