export const number = {

    /**
     * 숫자를 포맷 (콤마 추가)
     * @param {number|string} num - 포맷할 숫자
     * @returns {string} 포맷된 숫자
     */
    addCommas: (num) => {
        if (isNaN(num)) return '0'; // 숫자가 아닐 경우 0 반환
        return num
            .toString()
            .replace(/\B(?=(\d{3})+(?!\d))/g, ','); // 정규식을 이용해 3자리마다 콤마 추가
    },

    /**
     * 숫자에서 콤마를 제거
     * @param {string} num - 콤마가 포함된 숫자 문자열
     * @returns {string} 콤마가 제거된 숫자 문자열
     */
    removeCommas: (num) =>  {
        if (isNaN(num)) return '0'; // 숫자가 아닐 경우 0 반환
        return num.replace(/,/g, ''); // 모든 쉼표를 빈 문자열로 교체
    }

}