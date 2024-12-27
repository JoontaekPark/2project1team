package org.green.backend.controller.common;

import lombok.RequiredArgsConstructor;
import org.green.backend.service.common.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/api/v1/check/{id}")
    public int checkId(@PathVariable String id) {
        return userService.checkId(id);
    }

}
