package org.green.backend.dto.resume;

import lombok.Data;

import java.time.LocalDate;

/**
 * packageName    : org.green.frontend.dto.resume
 * fileName       : ResumeMillitaryDto
 * author         : 박준택
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        박준택       최초 생성
 */
@Data
public class ResumeMilitaryDto {

    // 이력서 병역사항 번호
    private Long resumeMilitaryNum;
    // 이력서 번호
    private Long resumeId;
    // 시작일자
    private LocalDate resumeMilitaryStrDate;
    // 종료일자
    private LocalDate resumeMilitaryEndDate;
    // 병역구분(군필, 미필, 면제, 복무중)
    private String resumeMilitaryGbnCd;
    // 계급(이병 ~ 대장)
    private String resumeMilitaryRankGbnCd;
    // 군별(육, 해, 공 기타등등)
    private String resumeMilitaryTypeGbnCd;
    // 전역구분(의가사, 만기, 불명예 등등)
    private String resumeMilitaryFinishGbnCd;
}
