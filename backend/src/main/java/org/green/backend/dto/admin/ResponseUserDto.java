package org.green.backend.dto.admin;

import lombok.Data;

/**
 * 패키지명        : org.green.backend.dto.admin
 * 파일명          : ResponseUserDto
 * 작성자          : 김상준
 * 일자            : 2025-01-04
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2025-01-04        김상준            최초 생성
 */

@Data
public class ResponseUserDto {

    private String id;

    private String name;

    private String birth;

    private String userGbnCd;

    private String userGbnNm;

    private String useYn;


}
