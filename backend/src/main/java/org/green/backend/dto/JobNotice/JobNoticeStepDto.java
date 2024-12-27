package org.green.backend.dto.JobNotice;

/**
 * packageName    : org.green.backend.dto.JobNotice
 * fileName       : JobNoticeStepDTO
 * author         : 최윤서
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        최윤서       최초 생성
 */

import lombok.Data;

/**
 * Created on 2024-12-27 by 최윤서
 */
@Data
public class JobNoticeStepDto {
    private int jobNoticeStepNum;    // 채용 절차 번호
    private int jobNoticeNum;        // 채용공고 번호
    private String jobNoticeStepDetail; // 절차 상세 내용
}
