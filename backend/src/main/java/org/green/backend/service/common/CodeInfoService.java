package org.green.backend.service.common;

import org.green.backend.dto.common.CodeInfoDto;

import java.util.List;

/**
 * 패키지명        : org.green.backend.service.common
 * 파일명          : CodeinfoService
 * 작성자          : 김상준
 * 일자            : 2024-12-27
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-27        김상준            최초 생성
 */

public interface CodeInfoService {

    List<CodeInfoDto> getCodeInfos(String upCd);

    CodeInfoDto getCodeInfo(String cd, String upCd);

    List<CodeInfoDto> getCodeInfosBySubCode(String upCd);


}
