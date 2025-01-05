package org.green.backend.dto.headhunting;

import lombok.Data;

@Data
public class CodeInfoDto2 {
    private String cd;       // 코드
    private String cdNm;     // 코드명 (예: Java, React)
    private String upCd;     // 상위 코드 (예: back_cd, front_cd)
}
