import {api, utills} from '/static/js/common/module/common_module.js';
import {ChartJsApi} from "/static/js/class/ChartJs.js";
import {KakaoMap} from "/static/js/class/KakaoMap.js";

$(function () {

    const employee = document.getElementById('employeeChart');
    const employeeChart = new ChartJsApi(employee, 'line', [], []);
    employeeChart.initializeChart();

    const revenue = document.getElementById('revenueChart');
    const revenueChart = new ChartJsApi(revenue, 'line', [], []);
    revenueChart.initializeChart();

    const addr = document.querySelector("#addr").innerHTML;
    const companyName = document.querySelector(".company-name .name").innerHTML;

    let kakaoMap = new KakaoMap("addrMap", addr, companyName);

    const id = document.querySelector("input[name=companyId]").value;
    api.get("/api/v1/company-info/extra/" + id)
        .then(res => {

            const datasets = {
                employee: [
                    {
                        type: 'bar',
                        label: '사원수',
                        data: res.body.employees.map((employee) => {
                            return employee.emploCnt
                        }),
                        borderWidth: 1
                    },
                    {
                        type: 'line',
                        label: '사원수',
                        data: res.body.employees.map((employee) => {
                            return employee.emploCnt
                        }),
                        borderWidth: 1
                    }
                ],
                revenue: [
                    {
                        type: 'bar',
                        label: '매출액',
                        data: res.body.revenuses.map((revenue) => {
                            return revenue.revenuePay
                        }),
                        borderWidth: 1
                    },
                    {
                        type: 'line',
                        label: '매출액',
                        data: res.body.revenuses.map((revenue) => {
                            return revenue.revenuePay
                        }),
                        borderWidth: 1
                    }
                ],
            };


            const labels = {
                employee: res.body.employees.map((employee) => {
                    return utills.date.toYearMonth(employee.emploDate)
                }),
                revenue: res.body.revenuses.map((revenue) => {
                    return revenue.revenueYear + "년"
                }),
            }

            employeeChart.updateData(labels.employee, datasets.employee);
            revenueChart.updateData(labels.revenue, datasets.revenue);
        })
        .catch(error => console.error(error));

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

    $(document).on("click", ".fa-heart", function () {

        let param = {
            target: document.querySelector("input[name=companyId]").value,
            likeGbnCd: "10",
            likedStatus: this.classList.contains("fas"),
        }

        if (confirm(param.likedStatus ? "관심기업을 취소 하시겠습니까?" : "관심기업을 등록 하시겠습니까?")) {

            api.post("/api/v1/like", param)
                .then(data => {
                    location.reload();
                })
                .catch(error => console.error(error));

        }
    })

    $(document).on("click", ".modal-close-btn", function () {
        document.querySelector(".modal-container").classList.remove("on");
    });

    document.querySelector(".star").parentElement.addEventListener("click", function () {

        api.get("/api/v1/pass-job-notice")
            .then(data => {
                let content = document.querySelector(".modal-step-one .modal-content");
                let html = "";

                for (let jobNotice of data.body) {
                    html =
                        `<div class="modal-notice-item ${jobNotice.starYn > 0 ? 'end' : ''}" data-jobNoticeNum="${jobNotice.jobNoticeNum}">
                            <div class="img">
                                <img src="${jobNotice.fileUrl}${jobNotice.fileNewName}${jobNotice.fileExt}"
                                onerror="this.src='/static/img/default_image.png'">
                            </div>
                            <div class="detail">
                                <div class="data">${jobNotice.jobNoticeTitle}</div>
                                <div class="data">
                                    <div>${jobNotice.jobNoticeCareerGbnNm}</div>
                                </div>
                                <div class="data">${jobNotice.jobNoticeArea}</div>
                            </div>
                                <span class="end-wrap">작성완료</span>
                        </div>`;
                }
                content.innerHTML = html;
                document.querySelector(".modal-step-one").classList.add("on");
                document.querySelector(".modal-step-two").classList.remove("on");
                document.querySelector(".modal-step-two").dataset.jobnoticenum = '';
                document.querySelector(".modal-container").classList.add("on");

            })
            .catch(error => console.error(error));
    })

    $(document).on("click", ".modal-notice-item", function () {

        if (this.classList.contains("end")) {
            alert("이미 별점을 부여한 공고입니다.");
        } else {
            document.querySelector(".modal-step-two").dataset.jobnoticenum = this.dataset.jobnoticenum;
            document.querySelector(".modal-step-one").classList.remove("on");
            document.querySelector(".modal-step-two").classList.add("on");
        }
    });

    $(document).on("click", ".modal-save-btn", function () {

        let param = {
            jobNoticeNum: document.querySelector(".modal-step-two").dataset.jobnoticenum,
            star: (document.querySelector("#rating-star").value / 2) ?? 0,
        }
        if (confirm(`${param.star}점을 저장하시겠습니까? 저장시 수정할 수 없습니다.`)) {
            api.post("/api/v1/star", param)
                .then(data => {
                    location.reload();
                })
                .catch(error => console.error(error));
        }
    });

    $(document).on("input", "#rating-star", function () {
        func.drawStar(this);
    });

    $(document).on("click", ".job-card", function () {
        let jobNoticeNum = this.dataset.jobnoticenum;

        location.href = "/job-notice-detail/" + jobNoticeNum;
    });
});

const func = {

    drawStar: (target) => {
        document.querySelector(`.rating-star span`).style.width = `${target.value * 10}%`;
    }

}