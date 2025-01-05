package org.green.frontend.dto.resume;

import lombok.Data;

import java.time.LocalDate;

/**
 * packageName    : org.green.frontend.dto.resume
 * fileName       : ResumeActiveDto
 * author         : 박준택
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        박준택       최초 생성
 */
@Data
public class ResumeActiveDto {

    private Long resumeActiveNum;
    // 이력서 번호
    private Long resumeId;
    // 활동 상세 내용
    private String resumeActiveDetail;
    // 활동 시작일
    private String resumeActiveStrDate;
    // 활동 종료일
    private String resumeActiveEndDate;
}
