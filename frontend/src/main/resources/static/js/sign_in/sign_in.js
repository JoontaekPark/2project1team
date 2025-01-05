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

        api.post("/sign-in", input.data)
            .then(data => {
                if (data.status === "SUCCESS") {
                    sessionStorage.set("user", data.body);

                    axios.post('/session', data.body)
                        .then(res => {
                            if (data.body.userGbnCd === '20') location.href = "/company" //기업
                            else if (data.body.userGbnCd === '30') location.href = "/admin" //관리자
                            else location.href = "/" //구직자
                        })
                        .catch(error => {
                            console.error(error);
                        });
                }

            })
            .catch(error => {
                if (error.response.data.status) func.msg("아이디 및 비밀번호를 확인해주세요.");
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