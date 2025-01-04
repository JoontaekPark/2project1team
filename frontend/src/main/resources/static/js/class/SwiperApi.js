export class SwiperApi {
    /**
     * 생성자: Swiper 인스턴스를 초기화합니다.
     * @param {string} selector - Swiper 컨테이너의 CSS 선택자.
     * @param {Object} [options={}] - Swiper 초기화 옵션.
     */
    constructor(selector, options = {}) {
        this.selector = selector;
        this.options = {
            loop: true,
            autoplay: {
                delay: 5000,
                disableOnInteraction: false,
            },
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
            pagination: {
                el: '.swiper-pagination',
                clickable: true,
            },
            ...options,
        };
        this.swiper = null;
        this.initSwiper();
    }

    /**
     * Swiper를 초기화합니다.
     */
    initSwiper() {
        if (!this.swiper) {
            this.swiper = new window.Swiper(this.selector, this.options); // Swiper.js의 Swiper 생성자를 명시적으로 호출
        }
    }
}
