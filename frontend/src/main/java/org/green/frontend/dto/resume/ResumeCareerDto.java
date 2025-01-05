package org.green.frontend.dto.resume;

import lombok.Data;

import java.time.LocalDate;

/**
 * packageName    : org.green.frontend.dto.resume
 * fileName       : ResumeCarrerDto
 * author         : 박준택
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        박준택       최초 생성
 */
@Data
public class ResumeCareerDto {
    // 이력서 경력 번호
    private Long resumeCareerNum;
    // 이력서 번호
    private Long resumeId;
    // 회사명
    private String resumeCareerCompanyNm;
    // 입사연월
    private String resumeCareerJoinDt;
    // 퇴사연월
    private String resumeCareerOutDt;
    // 부서명
    private String resumeCareerDmpt;
    // 직책
    private String resumeCareerPstn;
    // 담당업무
    private String resumeCareerDuties;
}
