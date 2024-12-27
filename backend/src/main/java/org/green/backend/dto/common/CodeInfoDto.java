package org.green.backend.dto.common;

import lombok.Data;

/**
 * 패키지명        : org.green.backend.dto.common
 * 파일명          : CodeInfoDto
 * 작성자          : 김상준
 * 일자            : 2024-12-27
 * 내용            : 공통코드 DTO
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-27        김상준            최초 생성
 */


@Data
public class CodeInfoDto {

    // 코드
    private String cd;
    // 코드명
    private String cdNm;
    // 상위
    private String upCd;
}
