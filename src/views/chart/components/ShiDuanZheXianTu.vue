<template>
    <div ref="chartRef"></div>
</template>

<script setup>
import { onMounted, ref, toRefs } from 'vue';
import { Chart } from '@antv/g2';

const props = defineProps({ config: Object })
const { config } = toRefs(props)

const chartRef = ref();

// Step 1: 注册主题
// registerTheme('newTheme', {
//   defaultColor: 'red',
// });

const initChart = () => {
  const chart = new Chart({
    ...config.value,
    container: chartRef.value,
    autoFit: true,
    forceFit: true,
  })

  chart
    .title({
      title: `时段${window.CONFIG.systemName}分析`,
    })
    // .interval()
    .data({
      type: 'fetch',
      value: '/api/hdfs/read-csv?path=/output/hdfsJobByHour/part-r-00000',
      format: 'csv',
      autoType: true,
      transform: [
        // {
        //   type: 'log'
        // },
        {
          type: 'map',
          callback: (val) => { val['时段'] = val['时间'] + '时'; return val; },
        },
        {
          type: 'sortBy',
          fields: ['时间'], // 根据 sold 字段排序
        },
        {
          type: 'fold',
          fields: window.CONFIG.dataHeader,
          key: window.CONFIG.chartX,
          value: window.CONFIG.chartY,
        },
      ],

    })
    .encode('x', '时段')
    .encode('y', window.CONFIG.chartY)
    .encode('color', window.CONFIG.chartX)
    .scale('x', {
        range: [0, 1],
    })
    .scale('y', {
        nice: true,
    })
    .axis('x', { title: null })
    .axis('y', { title: null })

    chart.line()

    // chart.line().encode('shape', 'smooth');

    chart.point().encode('shape', 'point').tooltip(false);


  chart.render();
  return chart;
}

onMounted(() => {
  console.log(chartRef.value)
  initChart()
})

</script>

<style lang="scss" scoped>

</style>
