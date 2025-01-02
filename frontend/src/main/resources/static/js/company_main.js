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
                    datasetsData[star.instDt] = {cnt: 0, value: 0};
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

    $(document).on("click", ".job-card", function () {
        func.jobNoticeClickEvent(this, genderChart, ageChart, applyChart);
    })


});

const func = {
    jobNoticeClickEvent: (ele, genderChart, ageChart, applyChart) => {

        let jobNoticeNum = ele.dataset.jobnoticenum;

        api.get('/api/v1/company-main/' + jobNoticeNum)
            .then(res => {

                const result = {
                    stacks: func.transformToLabelValue(func.countOccurrences(res.body.stack)),
                    genders: func.transformToLabelValue(func.countOccurrences(res.body.gender)),
                    ages: func.transformToLabelValue(func.countOccurrences(res.body.age))
                };

                const datasets = {
                    stack: [
                        {
                            type: 'line',
                            label: '기술스택',
                            data: result.stacks.map((stack) => {
                                return stack.value
                            }),
                            borderWidth: 1
                        },
                    ],
                    gender: [
                        {
                            label: '성별',
                            data: result.genders.map((gender) => {
                                return gender.value
                            }),
                            borderWidth: 1
                        },
                        
                    ],
                    age: [
                        {
                            label: '나이',
                            data: result.ages.map((age) => {
                                return age.value
                            }),
                            borderWidth: 1
                        },
                        
                    ],
                };


                const labels = {
                    stack: result.stacks.map((stack) => {
                        return stack.label
                    }),
                    gender: result.genders.map((gender) => {
                        return gender.label
                    }),
                    age: result.ages.map((age) => {
                        return age.label
                    }),
                }

                console.log(labels);
                console.log(datasets);

                genderChart.updateData(labels.gender, datasets.gender);
                ageChart.updateData(labels.age, datasets.age);
                applyChart.updateData(labels.stack, datasets.stack);

            })
            .catch(error => console.error(error));
    },

    countOccurrences: (array) => {
        return array.filter(item => item !== null) // Remove null values
            .reduce((acc, curr) => {
                acc[curr] = (acc[curr] || 0) + 1;
                return acc;
            }, {});
    },

    transformToLabelValue: (occurrences) => {
        return Object.entries(occurrences).map(([label, value]) => ({label, value}));
    }
}

