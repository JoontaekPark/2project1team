package org.green.frontend.dto.JobNotice;

import lombok.Data;

import java.util.List;

/**
 * Created on 2025-01-03 by 최윤서
 */
@Data
public class ApplyStatusResponseDto {

    private String userId;             // 지원자 ID (tbl_user - inst_id)
    private String resumeId;           // 이력서 ID (tbl_resume, tbl_apply_status)
    private int jobNoticeNum;          // 채용공고 번호 (tbl_job_notice)
    private String name;               // 지원자 이름 (tbl_user)
    private String genderCd;           // 지원자 성별 코드 (tbl_user)
    private String resumeTitle;        // 이력서 제목 (tbl_resume)
    private String instDt;             // 지원한 날짜 (tbl_apply_status)
    private String applyGbnCd;         // 합불 여부 코드 (tbl_apply_status)
    private String applyGbnName;         // 합불 여부 코드 (tbl_apply_status)

    private String stackNames;
    private List<String> stackCdList;  // 지원자 기술 스택 리스트 (tbl_resume_stack)

}
