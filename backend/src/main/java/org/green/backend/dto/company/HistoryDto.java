package org.green.backend.dto.company;

import lombok.Data;

import java.time.LocalDate;

/**
 * 패키지명        : org.green.backend.dto.company
 * 파일명          : HistoryDto
 * 작성자          : 김상준
 * 일자            : 2025-01-01
 * 내용            : 기업 연혁
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2025-01-01        김상준            최초 생성
 */

@Data
public class HistoryDto {
    private String id;
    // 연월
    private LocalDate historyDate;
    // 내용
    private String historyContent;
}
