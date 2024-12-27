async function registerBoard() {
    const data = {
        boardTarget:"user123", // 임시 저장
        boardTitle:"개발자 인원 수는 몇명인가요",
        boardContent:"회사 개발자 인원이 궁금해요",
        boardGbnCd:10,
        boardStatusCd:10,
        boardInstId: "ADMIN" //임시저장
    };

    const response = await fetch('/api/board/regist', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
    });

}