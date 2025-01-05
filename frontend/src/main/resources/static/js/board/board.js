// 1:1 문의 등록
function registerBoard() {
    const data = {
        //boardTarget: document.getElementById("boardTarget").value, // 문의 대상
        boardTarget: "넷플릭스2", // 임시 저장
        boardTitle: document.getElementById("boardTitle").value,
        boardContent: document.getElementById("boardContent").value,
        boardGbnCd: "10",
        boardStatusCd: "10",
        //instId: "tjsdud", // 임시 저장
    };

    api.post('/api/board/regist', data)
        .then(() => {
            alert("문의가 등록되었습니다.");
            window.location.href = "/board/list"
        })
        .catch(error => {
            console.error("등록 실패:", error);
            alert("등록에 실패했습니다.");
        });
}

// DOMContentLoaded 이벤트 핸들러
document.addEventListener('DOMContentLoaded', () => {
    const currentPath = window.location.pathname;

    if (currentPath.includes('/board/board-detail')) {
        fetchBoardDetail();
    } else if (currentPath.includes('/board/list')) {
        fetchBoardList();
    }
    const commentButton = document.getElementById("commentSubmitButton");
    if (commentButton) {
        commentButton.addEventListener("click", addReply);
    }
});

// 1:1 문의 리스트
function fetchBoardList() {
    //const userId = 'tjsdud';
    //const userGbnCd = '10';

    api.get('/api/board/list')
        .then(response => {
            console.log("리스트에서 응답을 받는지" + response.body); // 응답 데이터 출력
            console.log('Response as JSON:', JSON.stringify(response.body, null, 2)); // JSON
            const boardList = response.body;
            renderBoardList(boardList);
            console.log("상태 업데이트 응답 데이터:", response);
        })
        .catch(error => {
            console.error("리스트 조회 실패:", error);
            alert("리스트 조회에 실패했습니다.");
        });
}

function renderBoardList(boardList, userGbnCd) {
    const tableBody = document.getElementById('boardTableBody');
    tableBody.innerHTML = ''; // 기존 내용을 초기화

    const statusMap = {
        "10": "미확인",
        "20": "읽음",
        "30": "답변 완료"
    };

    boardList.forEach(board => {
        console.log(board.boardStatusCd); // 상태 코드 출력
        const row = document.createElement('tr');

        // 이름
        const nameCell = document.createElement('td');
        nameCell.textContent = userGbnCd === '10' ? board.targetName : board.userName;
        row.appendChild(nameCell);

        // 제목
        const titleCell = document.createElement("td");
        const link = document.createElement("a");
        link.href = `/board/board-detail?boardNum=${board.boardNum}`;
        link.textContent = board.boardTitle;
        titleCell.appendChild(link);

        // 상태 업데이트 후 이동 처리
        link.addEventListener('click', (e) => {
            e.preventDefault();
            if (board.boardStatusCd === "10") { // 상태가 미확인인 경우
                updateBoardStatus(board.boardNum, "20")
                    .then(() => {
                        board.boardStatusCd = "20"; // 상태 업데이트 반영
                        renderBoardList(boardList, userGbnCd); // 리스트 다시 렌더링
                        window.location.href = link.href; // 상세 페이지로 이동
                    })
                    .catch(error => {
                        console.error("상태 업데이트 실패:", error);
                        alert("상태 업데이트에 실패했습니다.");
                        window.location.href = link.href;
                    });
            } else {
                // 상태가 이미 "읽음" 이상이면 바로 이동
                window.location.href = link.href;
            }
            console.log("업데이트 후 상태 코드:", board.boardStatusCd);
        });
        titleCell.appendChild(link);
        row.appendChild(titleCell);

        // 상태
        const statusCell = document.createElement('td');
        statusCell.textContent = statusMap[board.boardStatusCd] || "알 수 없음";
        row.appendChild(statusCell);

        // 등록 날짜
        const dateCell = document.createElement('td');
        dateCell.textContent = `등록일자 ${board.instDt}`;
        row.appendChild(dateCell);

        tableBody.appendChild(row);
    });
}

// 상세 페이지
function fetchBoardDetail() {
    const urlParams = new URLSearchParams(window.location.search);
    const boardNum = urlParams.get("boardNum");

    if (!boardNum) {
        alert("잘못된 접근입니다.");
        return;
    }

    api.get('/api/board/detail', {boardNum})
        .then(async response => {
            const boardDetail = response.body;
            renderBoardDetail(boardDetail);

            // 현재 사용자 정보
            const currentUserId = "tjsdud"; // 현재 로그인한 사용자 ID
            const currentUserGbnCd = "10"; // 현재 사용자 구분 코드 (10: 구직자, 20: 기업)

            console.log("상태 업데이트 조건:");
            console.log("작성자:", boardDetail.instId);
            console.log("현재 사용자:", currentUserId);
            console.log("대상자:", boardDetail.boardTarget);
            console.log("상태 코드:", boardDetail.boardStatusCd);
            console.log("사용자 구분 코드:", currentUserGbnCd);
            // 상태 업데이트 조건
            if (
                currentUserGbnCd === "20" && // 기업 로그인
                boardDetail.instId !== currentUserId && // 게시글 작성자가 현재 사용자와 다름
                boardDetail.boardStatusCd === "10" // 상태가 미확인
            ) {
                console.log("기업 로그인 상태에서 상태 업데이트 조건 충족");
                updateBoardStatus(boardNum, "20");
            } else if (
                currentUserGbnCd === "10" && // 구직자 로그인
                boardDetail.boardTarget === currentUserId && // 대상자가 현재 사용자와 동일
                boardDetail.instId !== currentUserId && // 작성자가 현재 사용자가 아님
                boardDetail.boardStatusCd === "10" // 상태가 미확인
            ) {
                console.log("구직자 로그인 상태에서 상태 업데이트 조건 충족");
                updateBoardStatus(boardNum, "20");
            } else {
                console.log("상태 업데이트 조건을 충족하지 않음");
            }
        })
        .catch(error => {
            console.error("상세 조회 실패:", error);
            alert("상세 조회에 실패했습니다.");
        });
}

function renderBoardDetail(detail) {
    document.getElementById("boardTitle").textContent = `제목: ${detail.boardTitle}`;
    document.getElementById("boardContent").textContent = detail.boardContent;
    document.getElementById("instId").textContent = detail.instId;
    document.getElementById("instDt").textContent = detail.instDt;
    document.getElementById("instDt2").textContent = detail.instDt;

    const statusMap = {
        "10": "미확인",
        "20": "읽음",
        "30": "답변 완료"
    };


    const replyList = document.getElementById("replyList");
    replyList.innerHTML = "";

    detail.replies.forEach(reply => {
        const listItem = document.createElement("li");
        listItem.innerHTML = `
        <div class="content">
        <img src="/static/img/board_profile.png" alt="프로필">
        <p class="instidP"><span id="instId">${reply.instId}</span></p>
        <p class="pinst2">등록일 <span id="instDt2">${reply.instDt}</span></p>
        </div>
        <p class="board-content"><span id="boardContent">${reply.commentContent}</span></p>
        `;
        replyList.appendChild(listItem);
    });
}

// 댓글 추가
function addReply() {
    const commentContent = document.getElementById("commentInput").value;
    const boardNum = new URLSearchParams(window.location.search).get("boardNum");

    if (!boardNum) {
        alert("잘못된 접근입니다.");
        return;
    }

    if (!commentContent.trim()) {
        alert("댓글 내용을 입력하세요.");
        return;
    }

    api.post('/api/board/comment', {boardNum, commentContent, instId: "user1"})
        .then(() => {
            alert("댓글이 등록되었습니다.");
            document.getElementById("commentInput").value = ""; // 입력 필드 초기화
            updateBoardStatus(boardNum, "30");
            fetchBoardDetail();
        })
        .catch(error => {
            console.error("댓글 등록 실패:", error);
            alert("댓글 등록에 실패했습니다.");
        });
}

// 상태 관리
function updateBoardStatus(boardNum, boardStatusCd) {
    console.log("updateBoardStatus 호출:", {boardNum, boardStatusCd});

    return api.put('/api/board/update-status', {boardNum, boardStatusCd})
        .then(() => {
            console.log(`상태가 ${boardStatusCd}로 업데이트되었습니다.`);
        })
        .catch(error => {
            console.error("상태 업데이트 실패:", error);
            throw error;
        });
}

// 답글 폼



