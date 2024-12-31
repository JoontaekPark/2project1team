export const input = {
    /**
     * HTML form inputs에서 데이터를 가져오고 유효성 검사를 수행합니다.
     *
     * @param {string} required - 필수 입력 항목을 나타내는 클래스 이름 (기본값: "required").
     * @param {boolean} formData - true일 경우 FormData 객체를 포함하여 반환합니다.
     * @returns {object} 결과 객체: { result: boolean, data: object, formData?: FormData, error?: string, element?: HTMLElement }.
     */
    getByName: (required = "required", formData = false) => {
        const rejectType = ["button", "submit", "reset", "image"];
        const inputs = document.querySelectorAll("input, select");
        let res = { result: true, data: {} };

        for (let input of inputs) {
            if (!rejectType.includes(input.type)) {
                if (!input.name) {
                    return {
                        result: false,
                        error: "name 속성이 없는 input 요소가 있습니다.",
                        element: input
                    };
                }

                // 필수 입력값 확인
                const requiredError = handleRequired(input, required);
                if (requiredError) {
                    return {
                        result: false,
                        error: requiredError,
                        element: input
                    };
                }

                // 입력 타입별 데이터 처리
                if (input.tagName === "SELECT" && input.multiple) {
                    res.data[input.name] = handleSelectMultiple(input);
                } else if (input.type === "checkbox") {
                    if (!res.data[input.name]) {
                        res.data[input.name] = [];
                    }
                    if (input.checked) {
                        res.data[input.name].push(input.value);
                    }
                } else if (input.type === "radio") {
                    if (input.checked) {
                        res.data[input.name] = input.value;
                    }
                } else if (input.type === "file") {
                    const files = handleFileInput(input);
                    if (files.length > 0) {
                        res.data[input.name] = files;
                    } else {
                        delete res.data[input.name];
                    }
                } else {
                    if (res.data[input.name]) {
                        if (!Array.isArray(res.data[input.name])) {
                            res.data[input.name] = [res.data[input.name]];
                        }
                        res.data[input.name].push(input.value);
                    } else {
                        res.data[input.name] = input.value;
                    }
                }
            }
        }

        // FormData 객체 추가
        if (formData) {
            const formDataObj = new FormData();
            for (const key in res.data) {
                if (Array.isArray(res.data[key])) {
                    res.data[key].forEach(value => {
                        if (value instanceof File) {
                            formDataObj.append(key, value, value.name);
                        } else {
                            formDataObj.append(key, value);
                        }
                    });
                } else {
                    formDataObj.append(key, res.data[key]);
                }
            }
            res.formData = formDataObj;
        }

        return res;
    },

    setFormData: (data) => {
        const formDataObj = new FormData();
        for (const key in data) {
            if (Array.isArray(data[key])) {
                data[key].forEach(value => {
                    if (value instanceof File) {
                        formDataObj.append(key, value, value.name);
                    } else {
                        formDataObj.append(key, value);
                    }
                });
            } else {
                formDataObj.append(key, data[key]);
            }
        }

        return  formDataObj;
    }
};

/**
 * 필수 입력값 확인
 *
 * @param {HTMLElement} input - 검사할 input 요소.
 * @param {string} required - 필수 입력값을 나타내는 클래스 이름.
 * @returns {string|null} 에러 메시지 (에러가 없으면 null 반환).
 */
const handleRequired = (input, required) => {
    if (input.classList.contains(required)) {
        let msg = input.placeholder === "" ? `필수 입력값이 비어 있습니다: ${input.name}` : input.placeholder;
        if (input.type === "radio") {
            const radios = document.querySelectorAll(`input[name='${input.name}'][type='radio']`);
            const isChecked = Array.from(radios).some(radio => radio.checked);
            if (!isChecked) {
                return msg;
            }
        } else if (!input.value.trim() && input.type !== "file") {
            return msg;
        }
    }
    return null;
};

/**
 * 다중 선택 가능한 select 요소에서 선택된 값 가져오기
 *
 * @param {HTMLSelectElement} input - 다중 선택 select 요소.
 * @returns {Array} 선택된 값 배열.
 */
const handleSelectMultiple = (input) => {
    return Array.from(input.options)
        .filter(option => option.selected)
        .map(option => option.value);
};

/**
 * 파일 input 처리
 *
 * @param {HTMLInputElement} input - 파일 input 요소.
 * @returns {Array} 업로드된 파일 배열.
 */
const handleFileInput = (input) => {
    return input.files && input.files.length > 0 ? Array.from(input.files).map(file => file) : [];
};
