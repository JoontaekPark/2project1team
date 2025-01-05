// API 클라이언트 설정
const api = axios.create({
    baseURL: 'http://localhost:8080/api/head-hunting',
    timeout: 5000,
    headers: {
        'Content-Type': 'application/json',
    },
});

// 선택된 필터 저장
let selectedFilters = {
    stack: [],
    career: []
};

document.addEventListener('DOMContentLoaded', () => {
    loadInitialFilters();
});

function loadInitialFilters() {
    api.get('/filters')
        .then(response => {
            console.log(response);
            renderFilters(response.data.body);
        })
        .catch(error => {
            console.error('초기 필터 데이터 로드 실패:', error);
            alert('필터 데이터를 불러오는 데 실패했습니다.');
        });
}

function renderFilters(filters) {
    // 기술스택 카테고리 렌더링
    const stackContainer = document.getElementById('stack-category');
    filters.stackFilters.forEach(item => {
        const button = document.createElement('button');
        button.textContent = item.cdNm;
        button.dataset.value = item.cd; // 데이터 속성 추가
        button.onclick = () => toggleFilter('stack', item.cd); // 필터 선택/해제
        //button.onclick = () => loadCategoryDetails(item.cd); // 세부 스택 로드
        stackContainer.appendChild(button);
    });

    // 경력 렌더링
    const careerContainer = document.getElementById('career-options');
    filters.careerFilters.forEach(item => {
        const button = document.createElement('button');
        button.textContent = item.cdNm;
        button.dataset.value = item.cd; // 데이터 속성 추가
        button.onclick = () => toggleFilter('career', item.cd); // 필터 선택/해제
        careerContainer.appendChild(button);
    });
}

function toggleFilter(type, value) {
    const selectedList = selectedFilters[type];

    // 이미 선택된 필터일 경우 해제
    if (selectedList.includes(value)) {
        selectedFilters[type] = selectedList.filter(item => item !== value);
    } else {
        // 선택된 필터 추가
        selectedFilters[type].push(value);
    }

    console.log('현재 선택된 필터:', selectedFilters);
    updateFilterUI(type, value);
}

function updateFilterUI(type, value) {
    const buttons = document.querySelectorAll(`#${type}-options button`);
    buttons.forEach(button => {
        if (button.dataset.value === value) {
            button.classList.toggle('selected');
        }
    });
}

// 검색 버튼 구현
document.getElementById('search-button').addEventListener('click', () => {
    api.post('/search', selectedFilters)
        .then(response => {
            console.log('검색 결과:', response.data);
            renderSearchResults(response.data);
        })
        .catch(error => {
            console.error('검색 요청 실패:', error);
        });
});

// 검색 결과 렌더링 함수
function renderSearchResults(results) {
    const resultContainer = document.getElementById('search-results');
    resultContainer.innerHTML = ''; // 이전 결과 초기화

    results.forEach(item => {
        const card = document.createElement('div');
        card.classList.add('result-card');

        card.innerHTML = `
            <h3>${item.jobNoticeTitle}</h3>
            <p>${item.companyName}</p>
            <p>${item.jobNoticeArea}</p>
        `;

        resultContainer.appendChild(card);
    });
}
