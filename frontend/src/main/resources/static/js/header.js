import {api, sessionStorage} from "/static/js/common/module/common_module.js"


$(function () {

    document.querySelector("#sign-out-btn")?.addEventListener("click", function () {
        api.post("/sign-out")
            .then(data => {

                axios.post('/session')
                    .then(res => {
                        sessionStorage.remove("user");
                        location.href = "/sign-in"
                    })
                    .catch(error => {
                        console.error(error);
                    });

            })
            .catch(error => console.error(error));

    })


})