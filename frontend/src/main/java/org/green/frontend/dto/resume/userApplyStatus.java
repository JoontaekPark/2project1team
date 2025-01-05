package org.green.frontend.dto.resume;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class userApplyStatus {
    private int jobNoticeNum;    // job_notice_num
    private int resumeId;        // resume_id
    private String applyGbnCd;    // apply_gbn_cd
    private String instDt; // inst_dt
    private String resumeTitle;
    private String jobNoticeTitle;
}
