
/*
// API 클라이언트 설정
const api = axios.create({
    baseURL: 'http://localhost:8080/api/headhunting',
    timeout: 5000,
    headers: {
        'Content-Type': 'application/json',
    },
});

// 선택된 필터를 저장
let selectedFilters = {
    stacks: [],
    careers: []
};

// 페이지 로드 시 필터 데이터 가져오기
document.addEventListener('DOMContentLoaded', () => {
    loadFilters();
    setupEventListeners();
});

// 필터 데이터 가져오기
function loadFilters() {
    api.get('/filters')
        .then(response => {
            renderFilters(response.data);
        })
        .catch(error => {
            console.error('필터 데이터를 가져오는 중 오류 발생:', error);
        });
}


*/
