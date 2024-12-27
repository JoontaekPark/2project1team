$(function (){

    $(document).on("click", ".button", function (){

        let userGbnCd = this.dataset.user_gbn_cd;

        let url = new URL('/sign-up', location.origin);
        url.searchParams.set('userGbnCd', userGbnCd);

        location.href = url.toString();
    });

});