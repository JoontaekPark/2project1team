package org.green.frontend.dto.resume;

import lombok.Data;

@Data
public class GetCntApplyDto {

    private int totalCnt;
    private int failCnt;
    private int nocheckCnt;
    private int passCnt;
}
