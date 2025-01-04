package org.green.backend.dto.common;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 패키지명        : org.green.backend.dto.common
 * 파일명          : StarDto
 * 작성자          : 김상준
 * 일자            : 2025-01-04
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2025-01-04        김상준            최초 생성
 */

@Data
public class StarDto {

    // 아이디
    private String id;
    // 채용공고 번호
    private Long jobNoticeNum;
    // 평점 (0~5) 일단 소수점 제외
    private Float star;
    // 작성일시
    private LocalDate instDt;
}
