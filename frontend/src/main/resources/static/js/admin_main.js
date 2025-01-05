import {api} from "/static/js/common/module/common_module.js"

$(function () {

    $('.pagination button').on("click", function () {
        let page = this.dataset.page;

        if (page) {
            location.href = func.getUrl(page);
        }
    });

    $('#search-frm').on("submit", function (e) {
        e.preventDefault();

        location.href = func.getUrl();
    });

    $(document).on("click", ".filter", function (){

        const filters = document.querySelectorAll(".filter");

        for (let filter of filters){
            filter.classList.remove("active");
        }
        this.classList.add("active");

        location.href = func.getUrl();
    })

    $('tbody tr').on("click", function (e) {
        if ($(e.target).is('input[type=checkbox]')) {
            e.stopPropagation();
            return
        }

        // 체크박스를 찾고 상태를 토글
        let checkBox = this.querySelector("input[type=checkbox]");
        checkBox.checked = !checkBox.checked;

        func.chkAll();
    });

    $(document).on("change", "input[name=userChk]", function (){

        func.chkAll();
    });

    $(document).on("change", "input[name=userChkAll]", function (){

        let checkBoxs = document.querySelectorAll("input[name=userChk]");
        let checkBoxAll = document.querySelector("input[name=userChkAll]");

        for (let box of checkBoxs){
            box.checked = checkBoxAll.checked;
        }

    });

    $(document).on("click", "#status-btn", function (){

        let ids = document.querySelectorAll("input[name=userChk]:checked");

        let param = [];

        for (let id of ids){
            param.push(id.closest("tr").dataset.id);
        }

        api.post("/api/v1/admin/status", param)
            .then(data => {
                location.reload();
            })
            .catch(error => console.error(error));

    })

});

const func = {

    getUrl: (page = document.querySelector(".pagination button.active").dataset.page) => {
        const baseURL = '/admin'; // 기본 경로
        const url = new URL(baseURL, window.location.origin);
        const search = document.querySelector("input[name=search]").value || '';

        let filter = document.querySelector(".filter.active")?.dataset.usergbncd || '';
        console.log(filter);

        url.searchParams.append("filter", filter);
        url.searchParams.append("page", page);

        // 검색어 추가 (필요 시 주석 해제)
        url.searchParams.append("search", search);

        return url.toString();
    },

    chkAll: () => {

        let checkBoxs = document.querySelectorAll("input[name=userChk]");
        let checkedBoxs = document.querySelectorAll("input[name=userChk]:checked");

        let checkBoxAll = document.querySelector("input[name=userChkAll]");

        if (checkBoxs.length == checkedBoxs.length){
            checkBoxAll.checked = true;
        }else{
            checkBoxAll.checked = false;
        }

    }
};
