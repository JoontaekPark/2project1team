package org.green.frontend.dto.resume;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * packageName    : org.green.frontend.dto.resume
 * fileName       : ResumeAll2Dto
 * author         : 박준택
 * date           : 2025-01-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-01-02        박준택       최초 생성
 */
@Data
public class ResumeInfoAll2Dto {
//    private Long resumeId;
//    // 이력서 제목
//    private String resumeTitle;
//    // 희망근무 지역(작성)
//    private String resumeArea;
//    // Y: 맞음, N: 아님
//    private String resumeMainYn;
//    // 메모
//    private String resumeMemo;
//    // 작성자 ID
//    private String instId;

    private ResumeDto basicInfo;
    List<ResumeActiveDto> actives;
    List<ResumeCareerDto> careers;
    List<ResumeCertsDto> certs;
    List<ResumeEducationDto> educations;
    List<ResumeIntroduceDto> introduces;
    List<ResumeLoyaltyDto> loyalties;
    List<ResumeMilitaryDto> militaries;
    List<ResumePrtfDto> prtfs;
    List<ResumeStackDto> stacks;
}


