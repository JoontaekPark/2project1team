import {api, utills} from '/static/js/common/module/common_module.js';
import {ChartJsApi} from "/static/js/class/ChartJs.js";
import {SwiperApi} from "/static/js/class/SwiperApi.js";

$(function () {

    const jobNotice = new SwiperApi('.job-notice-swiper', {
        slidesPerView: 4, // 한 번에 보여줄 슬라이드 개수
        slidesPerGroup: 1, // 한 번에 이동할 슬라이드 개수
        loop: false
    });

    const banner = new SwiperApi('.banner-swiper', {
        slidesPerView: 1, // 한 번에 보여줄 슬라이드 개수
        slidesPerGroup: 1, // 한 번에 이동할 슬라이드 개수
        loop: true
    });

    const star = document.getElementById('starChart');
    const starChart = new ChartJsApi(star, 'line', [], [])
    starChart.initializeChart();

    const age = document.getElementById('ageChart');
    const ageChart = new ChartJsApi(age, 'pie', [], [])
    ageChart.initializeChart();

    const gender = document.getElementById('genderChart');
    const genderChart = new ChartJsApi(gender, 'pie', [], [])
    genderChart.initializeChart();

    const apply = document.getElementById('applyChart');
    const applyChart = new ChartJsApi(apply, 'line', [], [])
    applyChart.initializeChart();


    api.get('/api/v1/company-job-star')
        .then(res => {
            console.log(res);
            let datasetsData = {};

            for (let star of res.body) {
                if (!datasetsData[star.instDt]) {
                    datasetsData[star.instDt] = { cnt: 0, value: 0 };
                }
                datasetsData[star.instDt].cnt += 1;
                datasetsData[star.instDt].value =
                    ((datasetsData[star.instDt].cnt - 1) * datasetsData[star.instDt].value + star.star) / datasetsData[star.instDt].cnt;
            }

            const datasets = [
                {
                    type: 'line',
                    label: '별점',
                    data: Object.values(datasetsData).map(data => data.value),
                    borderWidth: 1
                },
                {
                    type: 'bar',
                    label: '별점',
                    data: Object.values(datasetsData).map(data => data.value),
                    borderWidth: 1
                },
            ];

            const labels = Object.keys(datasetsData);

            starChart.updateData(labels, datasets);
        })
        .catch(error => console.error(error));
});

