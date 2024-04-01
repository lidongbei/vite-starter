<script setup name="Jobs">
import Mock from 'mockjs';
import dayjs from 'dayjs'
import axios from 'axios';

import { useLoadingStore } from '@/store/loading'
const { loading, unLoading, reMsg } = useLoadingStore()


import { App } from 'ant-design-vue'
const { message } = App.useApp();

const doMock = () => {
  const data = []

  // for (item of type) {
  for (let month = 0; month < 12; month++) {
    for (let hour = 0; hour < 24; hour++) {
      let item;
      if (hour < 7) {
        item = Mock.mock({
          "时间": dayjs(new Date(2023, month, 2, hour, 0, 0)).format('YYYY-MM-DD HH:mm:ss'),
          "南京路|10-20": 1,
          "沈营路|5-10": 1,
          "白云路|7-10": 1,
          "中央大街|0-15": 1,
          "长安路|10-15": 1,
        })
      } else if (hour < 10) {
        item = Mock.mock({
          "时间": dayjs(new Date(2023, month, 2, hour, 0, 0)).format('YYYY-MM-DD HH:mm:ss'),
          "南京路|70-100": 1,
          "沈营路|90-120": 1,
          "白云路|60-80": 1,
          "中央大街|110-130": 1,
          "长安路|80-100": 1,
        })
      } else if (hour < 17) {
        item = Mock.mock({
          "时间": dayjs(new Date(2023, month, 2, hour, 0, 0)).format('YYYY-MM-DD HH:mm:ss'),
          "南京路|10-20": 1,
          "沈营路|5-10": 1,
          "白云路|7-10": 1,
          "中央大街|0-15": 1,
          "长安路|5-20": 1,
        })
      } else if (hour < 20) {
        item = Mock.mock({
          "时间": dayjs(new Date(2023, month, 2, hour, 0, 0)).format('YYYY-MM-DD HH:mm:ss'),
          "南京路|60-130": 1,
          "沈营路|70-120": 1,
          "白云路|90-120": 1,
          "中央大街|100-110": 1,
          "长安路|99-140": 1,
        })
      } else {
        item = Mock.mock({
          "时间": dayjs(new Date(2023, month, 2, hour, 0, 0)).format('YYYY-MM-DD HH:mm:ss'),
          "南京路|10-20": 1,
          "沈营路|5-10": 1,
          "白云路|7-10": 1,
          "中央大街|0-15": 1,
          "长安路|0-15": 1,
        })
      }

      data.push(item)
    }
  }
  // }

  return data
}

const doUpload = () => {
  loading('数据生成中。。。')
  const data = doMock()
  reMsg('上传数据中。。。')
  axios.post('/api/hdfs/upload-by-json', {
    data,
    header: ['时间', ...window.CONFIG.dataHeader]
  }).then(res => {
    console.log(res)
    message.success('数据上传成功')
  }).finally(() => {
    unLoading()
  })
}

const runJobs = () => {
  const jobs = ['hdfsJobByMonth', 'hdfsJobByHour']
  const jobPromises = jobs.map(job => axios.postForm(`/api/job/run/${job}`, { inPath: 'default' }))
  loading('任务运行中。。。')
  Promise.all(jobPromises).then(resArr => {
    console.log(resArr)
    message.success('任务运行成功')
  }).catch(err => {
    message.error(err.message)
  }).finally(() => {
    unLoading()
  })
}


</script>

<template>
  <div class="page-container">
    <a-flex gap="middle" vertical>
      <a-button type="primary" @click="doUpload">生成测试数据并上传</a-button>
      <a-button type="primary" danger @click="runJobs">运行计算任务</a-button>
    </a-flex>
  </div>
</template>

<style lang="scss" scoped>
.page-container {
  width: 300px;
  margin: 200px auto;
}
</style>
