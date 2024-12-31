import {api, sessionStorage, utills} from '/static/js/common/module/common_module.js';

$(function () {

    $(document).on("click", ".login-button", function (e) {

        e.preventDefault();

        func.msg("", false);

        let input = utills.input.getByName();

        if (!input.result) {
            func.msg(input.error);
            input.element.focus();
            return;
        }

        api.post("/api/v1/user-info/check", input.data)
            .then(data => {
                if (data.status === "SUCCESS") {
                    const base64Data = btoa(JSON.stringify(true)); // Base64 인코딩
                    const urlSafeData = encodeURIComponent(base64Data); // URL-safe 변환
                    location.href = `/user-info?data=${urlSafeData}`
                }

            })
            .catch(error => {
                if (error.response.data.status) func.msg(error.response.data.body.message);
            });

    });


    const func = {

        msg: (message, type = true) => {
            let errMsg = document.querySelector(".error-message");

            errMsg.innerText = message;

            if (type) {
                errMsg.classList.add("on");
            } else {
                errMsg.classList.remove('on');
            }
        }
    }


});