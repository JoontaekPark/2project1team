import {api, utills, sessionStorage} from '/static/js/common/module/common_module.js';
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

    $(document).on("click", ".bookmark", function (e) {

        e.stopPropagation();

        let param = {
            target: this.closest(".job-card").dataset.jobnoticenum,
            likeGbnCd: "30",
            likedStatus: this.classList.contains("on"),
        }

        if (confirm(param.likedStatus ? "채용공고를 스크랩 취소 하시겠습니까?" : "채용공고를 스크랩 하시겠습니까?")) {

            api.post("/api/v1/like", param)
                .then(data => {
                    location.reload();
                })
                .catch(error => console.error(error));

        }


    });

    $(document).on("click", ".job-card", function (){

        let jobNoticeNum = this.dataset.jobnoticenum;

        location.href = "/job-notice-detail/" + jobNoticeNum;

    });


});


