package org.green.frontend.service.common;

import org.green.frontend.dto.common.CodeInfoDto;

import java.util.List;

/**
 * 패키지명        : org.green.frontend.service.common
 * 파일명          : CodeInfoController
 * 작성자          : 김상준
 * 일자            : 2025-01-01
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2025-01-01        김상준            최초 생성
 */

public interface CodeInfoService {

    List<CodeInfoDto> getCodeInfos(String upCd) throws Exception;

}
