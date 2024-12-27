package org.green.frontend.controller;

import lombok.RequiredArgsConstructor;
import org.green.frontend.dto.common.CodeInfoDto;
import org.green.frontend.service.common.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 패키지명        : org.green.frontend.controller
 * 파일명          : UserController
 * 작성자          : 김상준
 * 일자            : 2024-12-27
 * 내용            : 사용자관리 (회원가입, 회원정보수정, 기업정보수정 등...)
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-27        김상준            최초 생성
 */

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/sign-up")
    public String signUpCheck() {
        return "sign_up/sign_up_check";
    }

    @GetMapping("/sign-up/{userGbnCd}")
    public String signUp(@PathVariable("userGbnCd") String userGbnCd, Model model) throws Exception {

        List<CodeInfoDto> genders = userService.genderInfo();

        model.addAttribute("userGbnCd", userGbnCd);
        model.addAttribute("genders", genders);

        return "sign_up/sign_up";
    }

}
