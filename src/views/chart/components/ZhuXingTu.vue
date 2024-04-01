<template>
    <div ref="chartRef">
    </div>
</template>

<script setup>
import { onMounted, ref, defineProps, toRefs } from 'vue';
import { Chart } from '@antv/g2';

const props = defineProps({ config: Object })
const { config } = toRefs(props)

const chartRef = ref()

const initChart = () => {
  const chart = new Chart({
    ...config.value,
    container: chartRef.value,
    autoFit: true,
    forceFit: true,
  })
  chart
    // .theme({ type: 'classicDark' })
    .title({
      title: `月${window.CONFIG.systemName}统计`,
      // subtitle: 'world',
    })
    .interval()
    
    .data({
      type: 'fetch',
      value: '/api/hdfs/read-csv?path=/output/hdfsJobByMonth/part-r-00000',
      format: 'csv',
      autoType: true,
      transform: [
        {
          type: 'log'
        },
        {
          type: 'map',
          callback: (val) => { val['年月'] = val['时间'] + '月'; return val; },
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
    .encode('x', '年月')
    .encode('y', window.CONFIG.chartY)
    .encode('color', window.CONFIG.chartX)
    
    // 横柱
    .coordinate({ transform: [{ type: 'transpose' }] })
    .encode('size', 20)
    .style('radiusTopLeft', 10)
    .style('radiusTopRight', 10)
    .style('radiusBottomRight', 10)
    .style('radiusBottomLeft', 10)

    .axis('x', { 
        // labelFormatter: (val) => dayjs(val).format('M') + '月',
        title: null,
        autoRotate: true,
    })
    .axis('y', {
        title: null
    })
    .transform({ type: 'stackY' })
    .interaction('tooltip', {
    })

    
  chart.render();
  return chart;
}

onMounted(() => {
    initChart()
})

</script>

<style lang="scss" scoped>

</style>