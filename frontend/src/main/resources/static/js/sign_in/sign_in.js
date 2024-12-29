import {common} from "/static/js/common/module/common.js";

$(function () {

    $(document).on("click", ".login-button", function (e) {

        e.preventDefault();

        func.msg("", false);

        let input = common.utills.input.getByName();

        if (!input.result) {
            func.msg(input.error);
            input.element.focus();
            return;
        }

        common.api.post("/sign-in", input.data)
            .then(data => {
                console.log(data);
            })
            .catch(error => {
                if (error.response.data.status) {
                    func.msg("아이디 및 비밀번호를 확인해주세요.");
                }
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