package org.green.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 패키지명        : org.green.frontend.controller
 * 파일명          : SignInController
 * 작성자          : 김상준
 * 일자            : 2024-12-28
 * 내용            : 로그인
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-28        김상준            최초 생성
 */

@Controller
public class SignInController {

    @GetMapping("/sign-in")
    public String signIn() {
        return "sign_in/sign_in";
    }

}
