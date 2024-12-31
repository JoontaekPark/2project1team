import {api, utills} from '/static/js/common/module/common_module.js';

$(function () {


    $(document).on("click", ".submit-btn", function () {
        let inputData = utills.input.getByName();
        console.log(inputData);

        if (!inputData.result) {
            alert(inputData.error);
            inputData.element.focus();
            return;
        }

        let extra = func.getExtraData();

        for (let key of Object.keys(extra)) inputData.data[key] = extra[key];

        api.put("/api/v1/company-info", inputData.data)
            .then(data => console.log(data))
            .catch(error => console.error(error));

    });

    $(document).on("click", ".remove-btn", function () {
        this.parentElement.remove();
    });

    $(document).on("click", ".add-btn", function () {
        let listId = this.dataset.list_id;
        openModal(listId);
    });

    $(document).on("click", ".modal-confirm-btn", function () {
        addItem();
    })

    $(document).on("click", ".modal-cancel-btn", function () {
        closeModal();
    })

    let currentListId = ''; // 현재 추가할 리스트의 ID (연혁, 사원 수, 매출액)

    // 모달 열기
    function openModal(listId) {
        currentListId = listId + '-list'; // 현재 작업 중인 리스트 ID 설정
        const modalDateInput = document.getElementById('modal-date');
        const modalContentInput = document.getElementById('modal-content');

        modalDateInput.value = ''; // 연월 초기화
        modalContentInput.value = ''; // 내용 초기화
        document.getElementById('modal').style.display = 'flex'; // 모달 열기

        // 연도만 입력하도록 설정
        if (currentListId === "sales-list") {
            modalDateInput.type = 'text'; // 텍스트 입력 필드로 변경
            modalDateInput.placeholder = 'YYYY'; // 연도만 입력하도록 안내
            modalDateInput.maxLength = 4; // 최대 입력 길이 설정 (연도)

            // 숫자만 입력되도록 이벤트 추가
            modalDateInput.addEventListener('input', enforceNumericYearInput);
        } else {
            modalDateInput.type = 'month'; // 월 입력 필드
            modalDateInput.placeholder = 'YYYY.MM'; // 연월 입력 안내

            // 다른 경우 이벤트 제거
            modalDateInput.removeEventListener('input', enforceNumericYearInput);
        }

        // modal-content 타입 설정
        if (currentListId === "history-list") {
            modalContentInput.type = "text";
        } else {
            modalContentInput.type = "number";
        }
    }

// 숫자만 입력되도록 제한 및 유효성 검사
    function enforceNumericYearInput(event) {
        const input = event.target;
        const value = input.value.replace(/[^0-9]/g, ''); // 숫자가 아닌 문자 제거
        input.value = value;

        const year = parseInt(value, 10);
        const minYear = 1900;
        const maxYear = new Date().getFullYear();

        // 유효하지 않은 범위 경고 및 초기화
        if (year && (year < minYear || year > maxYear)) {
        }
    }

    // 모달 닫기
    function closeModal() {
        document.getElementById('modal').style.display = 'none'; // 모달 닫기
    }

    // 항목 추가
    function addItem() {
        const date = document.getElementById('modal-date').value.trim(); // 입력된 연월
        const content = document.getElementById('modal-content').value.trim(); // 입력된 내용

        // 유효성 검사: 연월과 내용이 비어있으면 경고
        if (!date || !content) {
            alert('연월과 내용을 모두 입력하세요!');
            return;
        }

        // 항목 유형별 설정
        const listConfig = {
            "employees-list": {key: "emploDate", value: "emploCnt", suffix: " <b>명</b>"},
            "sales-list": {key: "revenueYear", value: "revenuePay", suffix: " <b>백만원</b>"},
            "history-list": {key: "historyDate", value: "historyContent", suffix: ""}
        };

        const config = listConfig[currentListId] || {}; // 현재 리스트 ID에 해당하는 설정
        const {key, value, suffix} = config;

        if (!key || !value) {
            console.error(`Invalid currentListId: ${currentListId}`);
            return;
        }

        // 항목 생성
        const list = document.getElementById(currentListId);
        const div = document.createElement('div');
        div.classList.add(currentListId === 'history-list' ? 'history-item' : 'input-item'); // 연혁만 줄내림 처리

        div.dataset[key] = date;
        div.dataset[value] = content;
        div.innerHTML = `
      <span class="date">${date}</span>
      <span class="content">${currentListId === 'history-list' ? content : utills.number.addCommas(content)} ${suffix}</span>
      <button class="remove-btn" type="button">X</button>
    `;

        // 중복 검사
        if (!func.chkDuplicate(list, date)) {
            alert("이미 중복된 일자입니다.");
            return;
        }

        list.appendChild(div); // 리스트에 항목 추가
        closeModal(); // 모달 닫기
    }


});

const func = {

    chkDuplicate: (box, date) => {

        let eles = box.querySelectorAll("div");

        for (let ele of eles) {
            for (let key of Object.keys(ele.dataset)) {
                if (key === "emploDate" || key === "revenueYear" || key === "historyDate") {
                    if (ele?.dataset[key] == date) {
                        return false;
                    }
                }
            }

        }
        return true;
    },

    getExtraData: () => {

        let employeesList = document.querySelector("#employees-list");
        let salesList = document.querySelector("#sales-list");
        let historyList = document.querySelector("#history-list");

        return {
            employee: func.getDetailes(employeesList),
            history: func.getDetailes(historyList),
            revenuse: func.getDetailes(salesList)
        }
    },

    getDetailes: (list) => {

        let dataList = list.querySelectorAll("div");
        let result = [];

        for (let data of dataList) {

            let temp = {}
            for (let key of Object.keys(data.dataset)) {
                if (key === "emploDate" || key === "historyDate") {
                    temp[key] = utills.date.toFullDate(data.dataset[key]);
                } else {
                    temp[key] = data.dataset[key];
                }
            }

            result.push(temp);
        }

        return result;
    }

}