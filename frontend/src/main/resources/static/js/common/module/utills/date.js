export const date = {

    /**
     * 입력을 풀 데이트 형식 (YYYY-MM-DD)으로 변환
     * @param {string} input - 입력 문자열 (YYYY 또는 YYYY-MM)
     * @returns {string|null} 변환된 날짜 문자열 (YYYY-MM-DD) 또는 null
     */
    toFullDate: (input) => {
        // 정규식 패턴
        const yearRegex = /^\d{4}$/; // YYYY 형식
        const yearMonthRegex = /^\d{4}-(0[1-9]|1[0-2])$/; // YYYY-MM 형식

        if (yearRegex.test(input)) {
            // YYYY 형식 -> YYYY-01-01
            return `${input}-01-01`;
        } else if (yearMonthRegex.test(input)) {
            // YYYY-MM 형식 -> YYYY-MM-01
            return `${input}-01`;
        } else {
            return input; // 유효하지 않은 경우 원래값 반환
        }
    },

    /**
     * 입력을 YYYY-MM 형식으로 변환
     * @param {string} input - 입력 문자열 (YYYY-MM-DD)
     * @returns {string} 변환된 날짜 문자열 (YYYY-MM)
     */
    toYearMonth: (input) => {
        // 정규식 패턴
        const fullDateRegex = /^\d{4}-(0[1-9]|1[0-2])-\d{2}$/; // YYYY-MM-DD 형식

        if (fullDateRegex.test(input)) {
            // YYYY-MM-DD 형식 -> YYYY-MM
            return input.substring(0, 7);
        } else {
            return input; // 유효하지 않은 경우 원래값 반환
        }
    }

};
