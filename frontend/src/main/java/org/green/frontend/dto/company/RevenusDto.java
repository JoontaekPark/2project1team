package org.green.frontend.dto.company;

import lombok.Data;

/**
 * 패키지명        : org.green.backend.dto.company
 * 파일명          : RevenusDto
 * 작성자          : 김상준
 * 일자            : 2025-01-01
 * 내용            : 기업 매출액
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2025-01-01        김상준            최초 생성
 */

@Data
public class RevenusDto {
    private String id;
    // 연
    private Integer revenueYear;
    // 금액(백만원)
    private Long revenuePay;
}
