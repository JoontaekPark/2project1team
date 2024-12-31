package org.green.backend.controller.common;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.green.backend.dto.common.SignInDto;
import org.green.backend.dto.common.UserDto;
import org.green.backend.service.common.UserService;
import org.green.backend.utils.CookieUtil;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * 패키지명        : org.green.backend.controller.common
 * 파일명          : UserInfoController
 * 작성자          : 김상준
 * 일자            : 2024-12-27
 * 내용            : 사용자 정보 컨트롤러
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-27        김상준            최초 생성
 */

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CookieUtil cookieUtil;

    @GetMapping("/api/v1/check/{id}")
    public int checkId(@PathVariable String id) {
        return userService.checkId(id);
    }

    @PostMapping("/api/v1/sign-up")
    public int signUp(@ModelAttribute UserDto user) throws IOException {
        return userService.save(user);
    }

    @PostMapping("/api/v1/user-info/check")
    public int userInfoCheck(@RequestBody SignInDto user,
                             HttpServletRequest request){
        String token = cookieUtil.getCookie(request, "Authorization");
        return userService.checkPw(token, user.getPw());
    }

    @GetMapping("/api/v1/user-info")
    public UserDto userInfo(HttpServletRequest request) throws Exception {

        String token = request.getHeader("Authorization");
        return userService.userInfo(token);
    }

    @PostMapping("/api/v1/user-info")
    public int userInfo(@ModelAttribute UserDto user,
                            @RequestParam("fileChk") boolean fileChk) throws IOException {

        System.out.println(user);
        return userService.edit(user, fileChk);
    }

}
