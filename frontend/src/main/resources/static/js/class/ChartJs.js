/**
 * ChartJs 클래스를 사용하여 Chart.js 차트를 생성하고 관리합니다.
 */
export class ChartJs {

    /**
     * @typedef {Object} Dataset
     * @property {string} label - 데이터셋의 레이블로, 범례와 툴팁에 표시됩니다.
     * @property {number[]} data - 차트에 표시될 데이터 값들의 배열입니다.
     * @property {string} [backgroundColor] - 데이터셋의 배경색을 지정합니다.
     * @property {string} [borderColor] - 데이터셋의 경계선 색상을 지정합니다.
     * @property {number} [borderWidth] - 데이터셋의 경계선 두께를 지정합니다.
     *
     * {
     *   label: 'Test1',
     *   data: [12, 19, 3, 5, 2, 3],
     *   backgroundColor: 'rgba(75, 192, 192, 0.2)',
     *   borderColor: 'rgba(75, 192, 192, 1)',
     *   borderWidth: 1
     * }
     */

    /**
     * ChartJs 인스턴스를 생성합니다.
     * @param {HTMLElement} ctx - 차트를 그릴 캔버스의 2D 컨텍스트입니다.
     * @param {string} [type='line'] - 생성할 차트의 타입입니다. 기본값은 'line'입니다.
     * @param {string[]} [labels=[]] - 차트의 라벨 배열입니다.
     * @param {Dataset[]} [datasets=[]] - 차트에 표시될 데이터셋 배열입니다.
     * @param {Object} [options={}] - 차트의 추가 옵션입니다.
     */
    constructor(ctx, type = 'line', labels = [], datasets = [], options = {}) {
        this.ctx = ctx;
        this.type = type;
        this.labels = labels;
        this.datasets = datasets;
        this.options = options;
        this.chart = null;
    }

    /**
     * 차트를 초기화하고 생성합니다.
     * 기본 옵션과 사용자 정의 옵션을 병합하여 차트를 설정합니다.
     */
    initializeChart() {
        const defaultOptions = {
            scales: {
                y: {
                    beginAtZero: true
                }
            },
            responsive: true,
            maintainAspectRatio: true
        };

        const combinedOptions = {...defaultOptions, ...this.options};

        this.chart = new Chart(this.ctx, {
            type: this.type,
            data: {
                labels: this.labels,
                datasets: this.datasets
            },
            options: combinedOptions
        });
    }

    /**
     * 차트의 데이터를 업데이트하고 다시 렌더링합니다.
     * @param {string[]} newLabels - 새로운 라벨 배열입니다.
     * @param {Dataset[]} newDatasets - 새로운 데이터셋 배열입니다.
     */
    updateData(newLabels, newDatasets) {
        if (this.chart) {
            this.chart.data.labels = newLabels;
            this.chart.data.datasets = newDatasets;
            this.chart.update();
        }
    }

    /**
     * 차트의 크기를 조절하고 다시 렌더링합니다.
     * @param {number} width - 새로운 너비 값입니다.
     * @param {number} height - 새로운 높이 값입니다.
     */
    resizeChart(width, height) {
        if (this.chart) {
            if (width !== undefined && height !== undefined) {
                this.chart.resize(width, height);
            } else {
                this.chart.resize();
            }
        }
    }

    /**
     * 차트에 이벤트 핸들러를 추가합니다.
     * @param {string} eventType - 이벤트 타입 (예: 'click', 'hover')입니다.
     * @param {function} handler - 이벤트 발생 시 실행될 함수입니다.
     */
    addEventHandler(eventType, handler) {
        if (this.chart) {
            this.chart.options.on[eventType] = handler;
            this.chart.update();
        }
    }

    /**
     * 현재 차트를 파괴하여 메모리를 해제합니다.
     */
    destroyChart() {
        if (this.chart) {
            this.chart.destroy();
        }
    }
}
