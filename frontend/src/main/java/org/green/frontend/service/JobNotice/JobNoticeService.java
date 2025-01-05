package org.green.frontend.service.JobNotice;

import org.green.frontend.dto.JobNotice.ApplyStatusRequestDto;
import org.green.frontend.dto.JobNotice.ApplyStatusResponseDto;
import org.green.frontend.dto.JobNotice.JobNoticeResponseDto;
import org.green.frontend.dto.common.CodeInfoDto;

import java.util.List;

/**
 * Created on 2024-12-30 by 최윤서
 */
public interface JobNoticeService {

    List<CodeInfoDto> careerInfo() throws Exception;

    List<CodeInfoDto> educationInfo() throws Exception;

    List<CodeInfoDto> workTypeInfo() throws Exception;

    List<CodeInfoDto> stackInfo() throws Exception;

    List<CodeInfoDto> applyInfo() throws Exception;

    JobNoticeResponseDto getJobNoticeDetail(Integer jobNoticeNum) throws Exception;

    List<ApplyStatusResponseDto> getApplyStatusList(Integer jobNoticeNum) throws Exception;

}
