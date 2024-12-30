package org.green.backend.dto.common;

import lombok.Data;

/**
 * 패키지명        : org.green.backend.dto.common
 * 파일명          : SignInDto
 * 작성자          : 김상준
 * 일자            : 2024-12-28
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-28        김상준            최초 생성
 */

@Data
public class SignInDto {

    private String id;
    private String pw;

}
