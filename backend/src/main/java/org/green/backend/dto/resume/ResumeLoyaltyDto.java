package org.green.backend.dto.resume;

import lombok.Data;

/**
 * packageName    : org.green.frontend.dto.resume
 * fileName       : ResumeLoyaltyDto
 * author         : 박준택
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        박준택       최초 생성
 */
@Data
public class ResumeLoyaltyDto {
    // 이력서 우대사항 번호
    private Long resumeLoyaltyNum;
    // 이력서 번호
    private Long resumeId;
    // 우대사항 내용
    private String resumeLoyaltyDetail;
}
