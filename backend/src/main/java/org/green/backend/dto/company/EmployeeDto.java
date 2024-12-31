package org.green.backend.dto.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * 패키지명        : org.green.backend.dto.company
 * 파일명          : employeeDto
 * 작성자          : 김상준
 * 일자            : 2025-01-01
 * 내용            : 기업 사원수 테이블
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2025-01-01        김상준            최초 생성
 */

@Data
public class EmployeeDto {
    // 아이디
    private String id;
    // 연월
    private LocalDate emploDate;
    // 사원수
    private Integer emploCnt;
}
