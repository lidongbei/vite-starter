<template>
    <div ref="chartRef"></div>
</template>

<script setup>
import { onMounted, ref, defineProps, toRefs } from 'vue';
import { Chart } from '@antv/g2';

const chartRef = ref();

const props = defineProps({ config: Object })
const { config } = toRefs(props)

const keys  = window.CONFIG.dataHeader

const initChart = () => {
    const chart = new Chart({
        ...config.value,
        container: chartRef.value,
        autoFit: true,
        forceFit: true,
    });

    chart.coordinate({ type: 'radial', innerRadius: 0.3, endAngle: Math.PI });

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
                    return { [window.CONFIG.chartX]: key, [window.CONFIG.chartY]: +(re.toFixed(0)) }
                    })
                    return result
            }
            },
            {
                type: 'sortBy',
                fields: [[window.CONFIG.chartY, true]]
            }
        ],

        })
        .encode('x', window.CONFIG.chartX)
        .encode('y', window.CONFIG.chartY)
        .scale('y', { type: 'sqrt' })
        .encode('color', window.CONFIG.chartX)

        // .scale('color', {
        //     range: '#BAE7FF-#1890FF-#0050B3',
        // })

        .encode('size', 40)
        .style('radius', 20)
        .label({
            text: window.CONFIG.chartY,
            position: 'outside',
            autoRotate: true,
            rotateToAlignArc: true,
            dx: 4,
        })
        .axis('x', { title: false })
        .axis('y', false)
        .animate('enter', { type: 'waveIn', duration: 1000 });

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