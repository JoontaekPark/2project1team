package org.green.backend.service.JobNotice;

import org.green.backend.dto.JobNotice.JobNoticeResponseDto;

/**
 * Created on 2024-12-30 by 최윤서
 */
public interface JobNoticeService {

    //리스트


    //조회
    public JobNoticeResponseDto getJobNoticeDetails(int jobNoticeNum);

    //등록
    public JobNoticeResponseDto registJobNotice(JobNoticeResponseDto dto);

    //수정

    //삭제
}
