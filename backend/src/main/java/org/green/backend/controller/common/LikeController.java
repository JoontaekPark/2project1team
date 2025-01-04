package org.green.backend.controller.common;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.green.backend.dto.common.LikeDto;
import org.green.backend.service.common.LikeService;
import org.green.backend.utils.CookieUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 패키지명        : org.green.backend.controller.common
 * 파일명          : LikeController
 * 작성자          : 김상준
 * 일자            : 2025-01-03
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2025-01-03        김상준            최초 생성
 */

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LikeController {

    private final CookieUtil cookieUtil;
    private final LikeService likeService;

    @PostMapping("/like")
    public void like(@RequestBody LikeDto like,
                     HttpServletRequest request) {

        String token = cookieUtil.getCookie(request, "Authorization");

        likeService.like(token, like);
    }

}
