import {api, utills} from '/static/js/common/module/common_module.js';
import {ChartJsApi} from "/static/js/class/ChartJs.js";
import {SwiperApi} from "/static/js/class/SwiperApi.js";

$(function () {

    const jobNotice = new SwiperApi('.job-notice-swiper', {
        slidesPerView: 5, // 한 번에 보여줄 슬라이드 개수
        slidesPerGroup: 3, // 한 번에 이동할 슬라이드 개수
        loop: false
    });

    const banner = new SwiperApi('.banner-swiper', {
        slidesPerView: 1, // 한 번에 보여줄 슬라이드 개수
        slidesPerGroup: 1, // 한 번에 이동할 슬라이드 개수
        loop: true
    });




});


