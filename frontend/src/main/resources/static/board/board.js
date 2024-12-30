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

// 1:1 문의 리스트
async function fetchBoardList() {
    const userId = 'user123';
    const userGbnCd = '10';

    try {
        const response = await axios.get('/api/board/list', {
            params: {
                userId: userId,
                userGbnId: userGbnCd
            }
        });

        const boardList = response.data;
        renderBoardList(boardList, userGbnCd);

    } catch (error) {
        console.error('데이터 전송이 제대로 처리 되지 않았습니다.', error);
        alert('데이터를 가져오는데 실패했습니다.')
    }
}

function renderBoardList(boardList, userGbnCd) {
    const tablebody = document.getElementById("")
}