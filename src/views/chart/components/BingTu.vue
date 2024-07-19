<template>
    <div ref="chartRef"></div>
</template>

<script setup>
import { onMounted, ref, toRefs } from 'vue';
import { Chart } from '@antv/g2';

const props = defineProps({ config: Object })
const { config } = toRefs(props)

const chartRef = ref();

const keys  = window.CONFIG.dataHeader

const initChart = () => {
  const chart = new Chart({
    ...config.value,
    container: chartRef.value,
    autoFit: true,
    forceFit: true,
  });

  chart.coordinate({ type: 'theta', innerRadius: 0.2, outerRadius: 0.8 });

  chart
    .title({
      title: `${window.CONFIG.systemName}分析`,
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
          type: 'custom',
          callback: (data) => {
            const result = keys.map(key => {
              const re = data.reduce((currentSum, item) =>  currentSum + item[key] , 0)
              return { [window.CONFIG.chartX]: key, [window.CONFIG.chartY]: +(re.toFixed(2)) }
            })
            return result
          }
        },
        {
          type: 'sortBy',
          fields: [[window.CONFIG.chartY, false]]
        }
      ],

    })
    .transform({ type: 'stackY' })
    .encode('y', window.CONFIG.chartY)
    .encode('color', window.CONFIG.chartX)
    // .scale('color', { palette: 'set2' })
    // .interaction('tooltip', {
    //   // showTitle: true,
    //   render: (a, b) => {
    //     console.log(a)
    //     console.log(b)
    //     return "<div>{{用电量}}<div>"
    //   }
    // })
    .label({
      text: window.CONFIG.chartY,
      fontWeight: 'bold',
      offset: 14,
    })
    .label({
      text: window.CONFIG.chartX,
      position: 'spider',
      // connectorDistance: 0,
      fontWeight: 'bold',
      // textBaseline: 'bottom',
      dy: -4,
    })

    // .style('radius', 4)
    // .style('stroke', '#fff')
    // .style('lineWidth', 2)
    // .animate('enter', { type: 'waveIn' })
    ;

  chart.render();
  return chart;
}

onMounted(() => {
//   console.log(chartRef.value)
  initChart()
})

</script>

<style scoped>

</style>
