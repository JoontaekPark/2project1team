package org.green.frontend.service.resumeService;

import org.green.frontend.dto.resume.ResumeDto;
import org.green.frontend.global.common.ApiResponse;

/**
 * packageName    : org.green.frontend.service.resumeService
 * fileName       : ResumeService
 * author         : 박준택
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        박준택       최초 생성
 */

public interface ResumeService {

    public ApiResponse<Object> regResume(ResumeDto resumeDto);
}
