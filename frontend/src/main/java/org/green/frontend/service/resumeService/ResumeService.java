package org.green.frontend.service.resumeService;

import org.green.frontend.dto.common.CodeInfoDto;
import org.green.frontend.dto.resume.ResumeDto;
import org.green.frontend.global.common.ApiResponse;

import java.util.List;

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

    public List<CodeInfoDto> certResult() throws Exception;

    public List<CodeInfoDto> educationLevel() throws Exception;

    public List<CodeInfoDto> stacks() throws Exception;

    public List<CodeInfoDto> resumeMilitaryGbnCd() throws Exception;

    public List<CodeInfoDto> resumeMilitaryRankGbnCd() throws Exception;

    public List<CodeInfoDto> resumeMilitaryTypeGbnCd() throws Exception;

    public List<CodeInfoDto> resumeMilitaryFinishGbnCd() throws Exception;

}
