import {common} from "/static/js/common/module/common.js";

$(function () {

    document.querySelector("#check-button").addEventListener("click", function () {

        let id = document.querySelector("#id").value;
        if (id !== "") {

            common.api.get("/api/v1/check/" + id)
                .then(data => {
                    let cnt = data.body;

                    if (cnt > 0){
                        alert("중복된 아이디입니다.");
                    }

                })
                .catch(error => console.error(error));

        }
    })


});