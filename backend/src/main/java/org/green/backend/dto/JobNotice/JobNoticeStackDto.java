package org.green.backend.dto.JobNotice;

/**
 * packageName    : org.green.backend.dto.JobNotice
 * fileName       : JobNoticeStackDTO
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
public class JobNoticeStackDto {
    private int jobNoticeStackNum;  // 기술스택 번호
    private int jobNoticeNum;       // 채용공고 번호
    private String stackCd;          // 기술스택 코드
}
