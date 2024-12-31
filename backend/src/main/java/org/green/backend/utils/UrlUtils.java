package org.green.backend.utils;

/**
 * 패키지명        : org.green.backend.utils
 * 파일명          : asd
 * 작성자          : 김상준
 * 일자            : 2024-12-31
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-31        김상준            최초 생성
 */

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

public class UrlUtils {

    /**
     * 현재 요청의 Base URL 가져오기
     *
     * @return Base URL (예: http://localhost:4000)
     */
    public static String getBaseUrl() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        if (request == null) {
            throw new IllegalStateException("HttpServletRequest is not available outside of a request context");
        }

        String scheme = request.getScheme(); // http 또는 https
        String serverName = request.getServerName(); // localhost 또는 도메인 이름
        int serverPort = request.getServerPort(); // 4000 등 포트 번호
        String contextPath = request.getContextPath(); // 애플리케이션 컨텍스트 경로

        // 기본 포트는 포트 번호를 생략
        if ((scheme.equals("http") && serverPort == 80) || (scheme.equals("https") && serverPort == 443)) {
            return scheme + "://" + serverName + contextPath;
        }
        return scheme + "://" + serverName + ":" + serverPort + contextPath;
    }

    public static String getFileUrl(int port) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        if (request == null) {
            throw new IllegalStateException("HttpServletRequest is not available outside of a request context");
        }

        String scheme = request.getScheme(); // http 또는 https
        String serverName = request.getServerName(); // localhost 또는 도메인 이름
        String contextPath = request.getContextPath(); // 애플리케이션 컨텍스트 경로

        // 기본 포트는 포트 번호를 생략
        if ((scheme.equals("http") && port == 80) || (scheme.equals("https") && port == 443)) {
            return scheme + "://" + serverName + contextPath;
        }
        return scheme + "://" + serverName + ":" + port + contextPath;
    }
}
