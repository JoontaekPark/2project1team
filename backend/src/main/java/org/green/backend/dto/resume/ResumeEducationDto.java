package org.green.backend.dto.resume;

import lombok.Data;

import java.time.LocalDate;

/**
 * packageName    : org.green.frontend.dto.resume
 * fileName       : ResumeEducationDto
 * author         : 박준택
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        박준택       최초 생성
 */
@Data
public class ResumeEducationDto {
    // 이력서 학력 번호
    private Long resumeEducationNum;
    // 이력서 번호
    private Long resumeId;
    // 학력구분(H: 고등학교, U: 대학, S: 석사, D: 박사, J: 전문대)
    private String resumeEducationGbnCd;
    // 학교이름
    private String resumeEducationSchoolNm;
    // 전공
    private String resumeEducationMajor;
    // 성적 (필수아님)
    private Float resumeEducationScore;
    // 입학날짜
    private LocalDate resumeEducationIndt;
    // 졸업날짜
    private LocalDate resumeEducationOutdt;
    // 편입여부(Y, N)
    private String resumeEducationTransferYn;
    // 지역
    private String resumeEducationResion;
}
