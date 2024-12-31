import {api, utills} from '/static/js/common/module/common_module.js';

$(function () {


    $(document).on("click", ".submit-btn", function (){
        let inputData = utills.input.getByName();
        console.log(inputData);

        if (!inputData.result){
            alert(inputData.error);
            inputData.element.focus();
            return;
        }


    });

    $(document).on("click", ".remove-btn", function (){
        this.parentElement.remove();
    });

    $(document).on("click", ".add-btn", function (){
        let listId = this.dataset.list_id;
        openModal(listId);
    });

    $(document).on("click", ".modal-confirm-btn", function (){
        addItem();
    })

    $(document).on("click", ".modal-cancel-btn", function (){
        closeModal();
    })

    let currentListId = ''; // 현재 추가할 리스트의 ID (연혁, 사원 수, 매출액)

    // 모달 열기
    function openModal(listId) {
        currentListId = listId + '-list'; // 현재 작업 중인 리스트 ID 설정
        document.getElementById('modal-date').value = ''; // 연월 초기화
        document.getElementById('modal-content').value = ''; // 내용 초기화
        document.getElementById('modal').style.display = 'flex'; // 모달 열기

        if (currentListId == "history-list"){
            document.querySelector("#modal-content").type = "text";
        }else{
            document.querySelector("#modal-content").type = "number";
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

        // 항목 생성
        const list = document.getElementById(currentListId);
        const div = document.createElement('div');
        
        let last;
        if (currentListId === "employees-list") last = " <b>명</b>";
        else if (currentListId === "sales-list") last = " <b>백만원</b>";
        else last = "";
        div.classList.add(currentListId === 'history-list' ? 'history-item' : 'input-item'); // 연혁만 줄내림 처리
        div.dataset.date = date;
        div.dataset.value = content;
        div.innerHTML = `
      <span class="date">${date}</span>
      <span class="content">${currentListId === 'history-list' ? content : utills.number.addCommas(content)} ${last}</span>
      <button class="remove-btn" type="button">X</button>
    `;

        let dupliChk = func.chkDuplicate(list, date)
        if (!dupliChk){
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

        for (let ele of eles){
            if(ele?.dataset?.date == date){
                return false;
            }
        }
        return true;
    }

}