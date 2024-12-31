package org.green.backend.dto.JobNotice;

/**
 * packageName    : org.green.backend.dto.JobNotice
 * fileName       : JobNoticeWelfareDto
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
public class JobNoticeWelfareDto {
    private int jobNoticeWelfareNum;    // 복리후생 번호
    private int jobNoticeNum;           // 채용공고 번호
    private String jobNoticeWelfareDetail; // 복리후생 내용
}
