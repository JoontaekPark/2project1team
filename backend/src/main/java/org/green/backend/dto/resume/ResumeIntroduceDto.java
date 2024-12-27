package org.green.backend.dto.resume;

import lombok.Data;

/**
 * packageName    : org.green.frontend.dto.resume
 * fileName       : ResumeIntroduceDto
 * author         : 박준택
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        박준택       최초 생성
 */
@Data
public class ResumeIntroduceDto {
    // 이력서 자기소개 번호
    private Long resumeIntroduceNum;
    // 이력서 번호
    private Long resumeId;
    // 자기소개 제목
    private String resumeIntroduceTitle;
    // 자기소개 내용
    private String resumeIntroduceContent;
}
