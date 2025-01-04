package org.green.frontend.dto.resume;

import lombok.Data;

/**
 * packageName    : org.green.frontend.dto.resume
 * fileName       : ResumeStackDto
 * author         : 박준택
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        박준택       최초 생성
 */
@Data
public class ResumeStackDto {
    // 이력서 기술스택 번호
    private Long resumeStackNum;
    // 이력서 번호
    private Long resumeId;
    // 기술스택
    private String stackCd;

    private String stackNm;
}
