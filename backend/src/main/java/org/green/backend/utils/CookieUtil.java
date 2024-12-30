package org.green.backend.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

/**
 * 패키지명        : org.green.backend.utils
 * 파일명          : CookieUtil
 * 작성자          : 김상준
 * 일자            : 2024-12-28
 * 내용            : 쿠키 유틸
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-28        김상준            최초 생성
 */

@Component
public class CookieUtil {

    @Value("${cookie.same-site:Lax}") // SameSite 기본값: Lax
    private String sameSite;

    @Value("${cookie.secure:false}") // Secure 기본값: false
    private boolean secure;

    @Value("${cookie.max-age:86400}") // Max-Age 기본값: 1일 (초 단위)
    private int maxAge;

    /**
     * 쿠키 생성
     *
     * @param key   key
     * @param value value
     * @return ResponseCookie
     */
    public ResponseCookie createCookie(String key, String value) {
        return ResponseCookie.from(key, value)
                .httpOnly(true)
                .secure(secure) // 환경에 따른 Secure 설정
                .sameSite(sameSite) // 환경에 따른 SameSite 설정
                .path("/")
                .maxAge(maxAge) // 쿠키의 유효 기간 설정
                .build();
    }

    /**
     * 쿠키 생성
     *
     * @param key   key
     * @param request HttpServletRequest
     * @return String(value)
     */
    public String getCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }
}
