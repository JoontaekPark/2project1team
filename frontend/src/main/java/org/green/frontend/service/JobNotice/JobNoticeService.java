package org.green.frontend.service.JobNotice;

import org.green.frontend.dto.common.CodeInfoDto;

import java.util.List;

/**
 * Created on 2024-12-30 by 최윤서
 */
public interface JobNoticeService {

    List<CodeInfoDto> careerInfo() throws Exception;

    List<CodeInfoDto> educationInfo() throws Exception;

    List<CodeInfoDto> stackInfo() throws Exception;


}
