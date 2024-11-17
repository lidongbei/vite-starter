<template>
    <div class="row">
        <div class="chart">
            <ShiDuanZheXianTu :config="config" />
        </div>
    </div>

    <div class="row">
        <div class="chart">
            <ZheXianTu :config="config" />
        </div>
        <div class="chart">
            <JingXiangTu :config="config" />
        </div>
    </div>

    <div class="row">
        <div class="chart">
            <ZhuXingTu :config="config" />
        </div>
        <div class="chart">
            <BingTu :config="config" />
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import BingTu from './components/BingTu.vue';
import ShiDuanZheXianTu from './components/ShiDuanZheXianTu.vue';
import ZheXianTu from './components/ZheXianTu.vue';
import ZhuXingTu from './components/ZhuXingTu.vue';
import JingXiangTu from './components/JingXiangTu.vue';
import theme from './theme/index.json'

const config = ref({
    // theme: 'dark',
    // theme: 'light',
    // theme: 'classic',
    // theme: 'classicDark',
    // theme: 'academy',
    theme,
    legend: {
    },
    // scale: {
        // color : { palette: 'sinebow' }
    // },
    height: 600,
})

</script>

<style scoped>
.chart {
    width: 100%;
}
.row {
    display: flex;
    width: 100%;
    height: 600px;
}
</style>