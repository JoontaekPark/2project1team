package org.green.frontend.dto.common;

import lombok.Data;

/**
 * 패키지명        : org.green.frontend.dto.common
 * 파일명          : SessionDto
 * 작성자          : 김상준
 * 일자            : 2025-01-03
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2025-01-03        김상준            최초 생성
 */

@Data
public class SessionDto {

    // 아이디
    private String id;
    // 구직자: 이름, 회사: 회사명
    private String name;
    // 사용여부
    private String useYn;
    // 사용자구분 (10: 구직자, 20:기업, 30:관리자)
    private String userGbnCd;
}
