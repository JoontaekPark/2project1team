package org.green.backend.dto.resume;

import lombok.Data;

import java.time.LocalDate;

/**
 * packageName    : org.green.frontend.dto.resume
 * fileName       : ResumeCertsDto
 * author         : 박준택
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        박준택       최초 생성
 */
@Data
public class ResumeCertsDto {
    // 이력서 자격증 번호
    private Long resumeCertsNum;
    // 이력서 번호
    private Long resumeId;
    // 자격증 취득일
    private LocalDate resumeCertsDt;
    // 자격증 명
    private String resumeCertsNm;
    // 자격증 발급기관
    private String resumeCertsPlace;
    // 합격여부(P: 필기, S:실기)
    private String resumeCertsGbnCd;
}

