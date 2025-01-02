// 1:1 문의 등록
async function registerBoard() {
    const data = {
        boardTarget:"company", // 임시 저장
        boardTitle: document.getElementById("boardTitle").value,
        boardContent: document.getElementById("boardContent").value,
        boardGbnCd:"10",
        boardStatusCd:"10",
        instId: "user2" //임시저장
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


document.addEventListener('DOMContentLoaded', async () => {
    const currentPath = window.location.pathname;

    if (currentPath.includes('/board/boardDetail')) {
        await fetchBoardDetail();
    } else if (currentPath.includes('/board/list')) {
        await fetchBoardList();
    }
});
// 1:1 문의 리스트
// 페이지 로드 시 실행
async function fetchBoardList() {
    const userId = 'user2';
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
        link.href = `/board/boardDetail?boardNum=${board.boardNum}`;
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
        /*
        api.get('/api/board/detail', { params: {boardNum} })
            .then(data => console.log(data))
            .catch(error => console.error(error));
        */
        console.log(response.data.body);

        const boardDetail = response.data.body;
        renderBoardDetail(boardDetail);

        //로그인 사용자 정보(구분 코드)
        const currentUserId = "user2";
        const currentUserGbnCd = "10";

        //업데이트 조건
        if(
            (currentUserGbnCd === "20" && boardDetail.instId !== currentUserId && boardDetail.boardStatusCd === "10") ||
            (currentUserGbnCd === "10" && boardDetail.boardTarget === currentUserId && boardDetail.boardStatusCd === "10")
        ) {
            await updateBoardStatus(boardNum, "20"); // 읽음으로 변경
        }

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

    const statusMap = {
        "10": "미확인",
        "20": "읽음",
        "30": "답변 완료"
    };

    document.getElementById("boardStatus").textContent = statusMap[detail.boardStatusCd] || "알 수 없음";

    const replyList = document.getElementById("replyList");
    replyList.innerHTML = "";

    detail.replies.forEach(reply => {
        const listItem = document.createElement("li");
        listItem.textContent = `${reply.instId} : ${reply.commentContent}`;
        replyList.appendChild(listItem);
    });
}
//댓글 추가
async function addReply() {
    console.log("addReply함수 호출 됨")
    const commentContent = document.getElementById("commentInput").value;
    const boardNum = new URLSearchParams(window.location.search).get("boardNum");

    console.log(boardNum);
    if (!boardNum) {
        alert("잘못된 접근입니다. 문의 번호를 확인할 수 없습니다.");
        return;
    }
    if(!commentContent.trim()) {
        alert("댓글 내용을 입력하세요");
        return;
    }

    try {
        const response = await axios.post(`http://localhost:8080/api/board/comment`, {
            boardNum: parseInt(boardNum, 10),
            commentContent: commentContent,
            instId: "user1" // 로그인한 사용자(테스트용 id)
        });

        alert("댓글이 등록되었습니다");
        document.getElementById("commentInput").value = ""; // 입력 필드 초기화
        await updateBoardStatus(boardNum, "30");
        fetchBoardDetail();
    } catch (error) {
        console.error("댓글 등록 실패", error);
        alert("댓글 등록에 실패했습니다.");
    }
}
document.getElementById("commentSubmitButton").addEventListener("click", addReply);

//상태 관리
async function updateBoardStatus(boardNum, boardStatusCd) {
    try {
        const response = await axios.put(`http://localhost:8080/api/board/updateStatus`, {
            boardNum: boardNum,
            boardStatusCd: boardStatusCd
        });
        console.log(`상태가 ${boardStatusCd}로 업데이트되었습니다.`);

        await fetchBoardDetail();

    } catch (error) {
        console.error("상태 업데이트 실패", error);
        alert("상태 업데이트에 실패 했습니다.")
    }
}
//답글 폼
function openReplyForm() {
    const replyForm = document.getElementById("replyForm");
    replyForm.style.display = replyForm.style.display === "none" ? "block" : "none";
}