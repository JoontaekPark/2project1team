package org.green.backend.service.JobNotice;

import org.apache.ibatis.annotations.Param;
import org.green.backend.dto.JobNotice.ApplyStatusRequestDto;
import org.green.backend.dto.JobNotice.ApplyStatusResponseDto;
import org.green.backend.dto.JobNotice.JobNoticeRequestDto;
import org.green.backend.dto.JobNotice.JobNoticeResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created on 2024-12-30 by 최윤서
 */
public interface JobNoticeService {

    //리스트
    List<JobNoticeResponseDto> getJobNoticeList(String token);

    //조회
    JobNoticeResponseDto getJobNoticeDetails(int jobNoticeNum, String token);

    //등록
    void createJobNotice(JobNoticeRequestDto dto, String token) throws IOException;


    //지원현황
    List<ApplyStatusResponseDto> getApplyStatusList(int jobNoticeNum, String token);

    void updateStatus(ApplyStatusRequestDto dto) throws IOException;

}
