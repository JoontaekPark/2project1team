package org.green.backend.dto.JobNotice;

import lombok.Data;

/**
 * Created on 2025-01-04 by 최윤서
 */
@Data
public class ApplyStatusRequestDto {
    private int jobNoticeNum;
    private String resumeId;
    private String applyGbnCd;
}
