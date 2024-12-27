package org.green.backend.dto.resume;

import lombok.Data;

import java.time.LocalDate;

/**
 * packageName    : org.green.frontend.dto.resume
 * fileName       : ResumePrtfDto
 * author         : 박준택
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        박준택       최초 생성
 */
@Data
public class ResumePrtfDto {
    // 이력서 포트폴리오 번호
    private Long resumePrtfNum;
    // 이력서 번호
    private Long resumeId;
    // 작업시작기간
    private LocalDate resumePrtfStrDate;
    // 작업종료기간
    private LocalDate resumePrtfEndDate;
    // url
    private String resumePrtfUrl;
    // 작업인원
    private Integer resumePrtfCnt;
    // 작업내용
    private String resumePrtfContent;
}
