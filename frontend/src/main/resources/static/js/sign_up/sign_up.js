import { api, utills } from '/static/js/common/module/common_module.js';

$(function () {

    let chk = {
        id: false,
        pw: false
    }

    $(document).on("click", "#check-button", function () {

        let id = document.querySelector("#id");

        if (id.value === "") {
            func.msg(id, "아이디를 입력하세요.", "bad");
            return;
        }
        api.get("/api/v1/check/" + id.value)
            .then(data => {
                let cnt = data.body;

                if (cnt > 0) {
                    id.focus();
                    func.msg(id, "중복되는 아이디 입니다.", "bad");
                    chk.id = false;
                    return false;
                }

                func.msg(id, "사용가능한 아이디 입니다.", "good");
                chk.id = true;
            })
            .catch(error => console.error(error));

    });

    $(document).on("keyup", "#id", function () {
        chk.id = false;
        func.msg(id, "아이디 중복체크를 해주세요", "bad");
    });

    $(document).on("keyup", "#pw", function () {
        func.typePw(this, chk);
        func.confirmPw(chk);
    });

    $(document).on("keyup", "#confirmPw", function () {
        func.confirmPw(chk);
    });

    $(document).on("click", ".find-address", function () {
        func.daumPostApi();
    });

    $(document).on("click", ".image-placeholder", function () {
        let fileInput = document.querySelector("#profile");
        fileInput.click();
    });

    $(document).on("change", "#profile", function () {

        let file = this.files;

        if (file.length > 0) {

            const fileType = file[0].type;

            if (!fileType.includes('image')) {
                alert(`해당 파일은 이미지 파일이 아닙니다. 이미지(JPG,JPEG,GIF,PNG, 등..)을 업로드 해주세요.`);
                document.querySelector("#profile").value = "";
                return;
            }

            const preview = document.querySelector('.image');
            preview.src = URL.createObjectURL(file[0]);
            document.querySelector(".del-img").classList.add("on");
        }

    });

    $(document).on("click", ".del-img", function (e) {

        e.stopPropagation();

        document.querySelector(".del-img").classList.remove("on");
        let file = document.querySelector("#profile");

        const preview = document.querySelector('.image');
        file.value = "";

        URL.revokeObjectURL(preview.src);
        preview.src = "";


    });

    $(document).on("click", ".submit-button", function () {

        if (!chk.id) {
            func.msg(document.querySelector("#id"), "중복체크를 진행하세요.", "bad");
            return;
        }

        if (!chk.pw) {
            func.msg(document.querySelector("#confirmPw"), "비밀번호를 확인하세요.", "bad");
            return;
        }

        let labels = document.querySelectorAll(".input-group .bad");

        for (let label of labels) {
            label.classList.remove("bad");
            label.innerText = "";
        }

        let data = utills.input.getByName("required", true);

        if (!data.result) {
            data.element.focus();
            func.msg(data.element, data.error, "bad");
            return;
        }
        console.log(data);

        api.post("/api/v1/sign-up", data.formData, {"Content-Type": "multipart/form-data"})
            .then(data => {
                if (data.status === "SUCCESS") {
                    location.href = "/sign-in";
                }
            })
            .catch(error => {
                if (error.response.data.status === "ERROR") {
                    alert(error.response.data.body.message);
                }
            });
    });


});

const func = {
    msg: (target, msg = "", type = "") => {

        let msgLabel = target.closest(".input-group").querySelector(".msg-label");

        if (type === "good") {
            msgLabel.innerText = msg;
            msgLabel.classList.remove("bad");
            msgLabel.classList.add("good");
        } else if (type === "bad") {
            msgLabel.innerText = msg;
            msgLabel.classList.add("bad");
            msgLabel.classList.remove("good");
        } else {
            msgLabel.innerText = "";
            msgLabel.classList.remove("bad");
            msgLabel.classList.remove("good");
        }

    },

    confirmPw: (chk) => {
        let pw = document.querySelector("#pw");
        let confirmPw = document.querySelector("#confirmPw");
        chk.pw = false;

        if (pw.value === confirmPw.value) {
            func.msg(confirmPw);
            chk.pw = true;
        } else {
            func.msg(confirmPw, "비밀번호가 일치하지 않습니다.", "bad");
        }
    },

    typePw: (pw, chk) => {

        const minLength = 8;
        const lowercaseRegex = /[a-z]/;
        const numberRegex = /\d/;

        chk.pw = false;
        if (!lowercaseRegex.test(pw.value)) {
            func.msg(pw, "비밀번호에 최소 하나의 소문자가 포함되어야 합니다.", "bad");
            return;
        }
        if (!numberRegex.test(pw.value)) {
            func.msg(pw, "비밀번호에 최소 하나의 숫자가 포함되어야 합니다.", "bad");
            return;
        }
        if (pw.value.length < minLength) {
            func.msg(pw, "비밀번호는 최소 8자 이상이어야 합니다.", "bad");
            return;
        }

        chk.pw = true;
        func.msg(pw);
    },

    daumPostApi: () => {
        new daum.Postcode({
            oncomplete: function (data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                let addr = data.jibunAddress;
                let detailAddr = '';

                if (data.userSelectedType === 'R') {
                    addr = data.roadAddress;
                }

                if (data.userSelectedType === 'R') {

                    if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                        detailAddr += data.bname;
                    }

                    if (data.buildingName !== '' && data.apartment === 'Y') {
                        detailAddr += (detailAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }

                    if (detailAddr !== '') {
                        detailAddr = ' (' + detailAddr + ')';
                    }
                    console.log(detailAddr);
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zipCd').value = data.zonecode;
                document.getElementById("addr").value = addr;
                document.getElementById("addrDetail").value = detailAddr;
            }
        }).open();
    }


}