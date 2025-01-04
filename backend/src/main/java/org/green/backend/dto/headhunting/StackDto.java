package org.green.backend.dto.headhunting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class StackDto {
    private String cd;       // 코드
    private String cdNm;     // 코드명 (예: Java, React)
    private String upCd;     // 상위 코드 (예: back_cd, front_cd)
}
