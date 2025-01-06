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
    // 초기화 작업
    loadInitialFilters();

    // 검색 버튼 이벤트
    const searchButton = document.getElementById('search-button');
    if (searchButton) {
        searchButton.addEventListener('click', searchJobs);
    } else {
        console.error('search-button 요소를 찾을 수 없습니다.');
    }

    // 초기화 버튼 이벤트
    const resetButton = document.getElementById('reset-button');
    if (resetButton) {
        resetButton.addEventListener('click', resetFilters);
    } else {
        console.error('reset-button 요소를 찾을 수 없습니다.');
    }
});

// 초기 필터 데이터 로드
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

// 필터 렌더링
function renderFilters(filters) {
    const stackContainer = document.getElementById('stack-category');
    filters.stackFilters.forEach(item => {
        const button = document.createElement('button');
        button.textContent = item.cdNm;
        button.dataset.value = item.cd; // 데이터 속성 추가
        button.onclick = () => toggleFilter('stack', item.cd); // 필터 선택/해제
        stackContainer.appendChild(button);
    });

    const careerContainer = document.getElementById('career-options');
    filters.careerFilters.forEach(item => {
        const button = document.createElement('button');
        button.textContent = item.cdNm;
        button.dataset.value = item.cd; // 데이터 속성 추가
        button.onclick = () => toggleFilter('career', item.cd); // 필터 선택/해제
        careerContainer.appendChild(button);
    });
}

// 필터 선택/해제
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

// UI 업데이트
function updateFilterUI(type, value) {
    const containerId = type === 'stack' ? 'stack-category' : 'career-options';
    const buttons = document.querySelectorAll(`#${containerId} button`);
    buttons.forEach(button => {
        if (button.dataset.value === value) {
            button.classList.toggle('selected');
        }
    });
}

// 검색
function searchJobs() {
    console.log('선택된 필터로 검색 요청:', selectedFilters);
    api.post('/search', {
        stackFilters: selectedFilters.stack,
        careerFilters: selectedFilters.career
    })
        .then(response => {
            console.log('검색 결과:', response.data);
            renderSearchResults(response.data);
        })
        .catch(error => {
            console.error('검색 요청 실패:', error);
        });
}

// 검색 결과 렌더링
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

// 초기화
function resetFilters() {
    selectedFilters = { stack: [], career: [] };
    document.querySelectorAll('button.selected').forEach(button => {
        button.classList.remove('selected');
    });
    console.log('필터 초기화 완료:', selectedFilters);
}
