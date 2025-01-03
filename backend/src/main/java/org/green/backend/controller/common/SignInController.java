package org.green.backend.controller.common;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.green.backend.dto.common.SecurityUserDto;
import org.green.backend.dto.common.SignInDto;
import org.green.backend.service.common.SignInService;
import org.green.backend.utils.CookieUtil;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 패키지명        : org.green.backend.controller.common
 * 파일명          : Login
 * 작성자          : 김상준
 * 일자            : 2024-12-28
 * 내용            : 로그인 컨트롤러
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-28        김상준            최초 생성
 */

@RestController
@RequiredArgsConstructor
public class SignInController {

    private final SignInService signInService;
    private final CookieUtil cookieUtil;


    @PostMapping("/sign-in")
    public Map<String, String> signIn(@RequestBody SignInDto dto,
                                      HttpServletResponse response) {

        String token = signInService.signIn(dto);
        ResponseCookie cookie = cookieUtil.createCookie("Authorization", token);

        response.addHeader("Set-Cookie", cookie.toString());

        return signInService.loginInfo(token);
    }

    @PostMapping("/sign-out")
    public void signOut(HttpServletResponse response) {
        ResponseCookie cookie = cookieUtil.deleteCookie("Authorization");
        response.addHeader("Set-Cookie", cookie.toString());
    }

}
