package org.green.backend.dto.common;

import lombok.Data;

/**
 * 패키지명        : org.green.backend.dto.common
 * 파일명          : SecurityUserDto
 * 작성자          : 김상준
 * 일자            : 2024-12-28
 * 내용            : 시큐리티 userDto
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-28        김상준            최초 생성
 */

@Data
public class SecurityUserDto {

    private String id;
    // 비밀번호
    private String pw;
    // 구직자: 이름, 회사: 회사명
    private String name;
    // 사용여부
    private String useYn;
    // 사용자구분 (10: 구직자, 20:기업, 30:관리자)
    private String userGbnCd;
    private Long expiredMs;    // 토큰 만료 시간
}
