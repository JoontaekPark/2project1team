// 1:1 문의 등록
async function registerBoard() {
    const data = {
        boardTarget:"user123", // 임시 저장
        boardTitle: document.getElementById("boardTitle").value,
        boardContent: document.getElementById("boardContent").value,
        boardGbnCd:"10",
        boardStatusCd:"10",
        instId: "ADMIN" //임시저장
    };

    const response = await fetch('http://localhost:8080/api/board/regist', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
    });

    console.log('Request Data:', data); // 전송 데이터 확인
    console.log('Response Status:', response.status); //

    if(response.ok) {
        alert("문의가 등록되었습니다.")
    } else {
        alert("등록에 실패했습니다.")
    }
}


document.addEventListener('DOMContentLoaded', () => {
    const currentPath = window.location.pathname;

    if (currentPath.includes('/board/details')) {
        fetchBoardDetail();
    } else if (currentPath.includes('/board/list')) {
        fetchBoardList();
    }
});
// 1:1 문의 리스트
// 페이지 로드 시 실행
async function fetchBoardList() {
    const userId = 'user1';
    const userGbnCd = '10';

    try {
        const response = await axios.get('http://localhost:8080/api/board/list', {
            params: {
                userId: userId,
                userGbnId: userGbnCd
            }
        });

        const boardList = response.data.body;
        console.log(boardList);
        renderBoardList(boardList, userGbnCd);

    } catch (error) {
        console.error('데이터 전송이 제대로 처리 되지 않았습니다.', error);
        alert('데이터를 가져오는데 실패했습니다.')
    }
}

function renderBoardList(boardList, userGbnCd) {
    const tableBody = document.getElementById('boardTableBody');
    tableBody.innerHTML = ''; // 기존 내용을 초기화

    boardList.forEach((board) => {
        const row = document.createElement('tr');

        // 이름
        const nameCell = document.createElement('td');
        nameCell.textContent = userGbnCd === '10' ? board.targetName : board.userName;
        row.appendChild(nameCell);

        // 제목
        const titleCell = document.createElement('td');
        const link = document.createElement('a');
        link.href = `/board/details?boardNum=${board.boardNum}`;
        link.textContent = board.boardTitle;
        titleCell.appendChild(link);
        row.appendChild(titleCell);

        // 등록 날짜
        const dateCell = document.createElement('td');
        dateCell.textContent = board.instDt;
        row.appendChild(dateCell);

        tableBody.appendChild(row);
    });
}
//상세페이지
async function fetchBoardDetail() {
    const urlParams = new URLSearchParams(window.location.search);
    const boardNum = urlParams.get("boardNum");

    if(!boardNum) {
        alert("잘못된 접근입니다")
        return;
    }

    try {
        const response = await axios.get(`http://localhost:8080/api/board/detail`, {
            params: {boardNum}
        });
        console.log(response.data.body);
        const boardDetail = response.data.body;


        renderBoardDetail(boardDetail);
    } catch (error) {
        console.error("데이터 로드 실패", error);
        alert("문의 데이터를 가져오는데 실패 했습니다.");
    }

}

function renderBoardDetail(detail) {
    document.getElementById("boardTitle").textContent = detail.boardTitle;
    document.getElementById("boardContent").textContent = detail.boardContent;
    document.getElementById("instId").textContent = detail.instId;
    document.getElementById("instDt").textContent = detail.instDt;

    const replyList = document.getElementById("replyList");
    replyList.innerHTML = "";

    detail.replies.forEach(reply => {
        const listItem = document.createElement("li");
        listItem.textContent = `${reply.instId} : ${reply.content}`;
        replyList.appendChild(listItem);
    });
}

