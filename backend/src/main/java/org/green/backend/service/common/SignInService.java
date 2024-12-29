package org.green.backend.service.common;

import org.green.backend.dto.common.SecurityUserDto;
import org.green.backend.dto.common.SignInDto;

import java.util.Map;

/**
 * 패키지명        : org.green.backend.service.common
 * 파일명          : SignInService
 * 작성자          : 김상준
 * 일자            : 2024-12-28
 * 내용            : 로그인 서비스 인터페이스
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-28        김상준            최초 생성
 */

public interface SignInService {

    public String signIn(SignInDto dto);

    public Map<String, String> loginInfo(String token);

}
