package org.green.frontend.service.common;

import org.green.frontend.dto.common.CodeInfoDto;

import java.util.List;

/**
 * 패키지명        : org.green.frontend.service.common
 * 파일명          : UserService
 * 작성자          : 김상준
 * 일자            : 2024-12-27
 * 내용            : 사용자서비스 인터페이스
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-27        김상준            최초 생성
 */

public interface UserService {

    List<CodeInfoDto> genderInfo() throws Exception;

}
