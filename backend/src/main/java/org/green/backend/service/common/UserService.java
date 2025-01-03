package org.green.backend.service.common;

import org.green.backend.dto.common.UserDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * 패키지명        : org.green.backend.service.common
 * 파일명          : UserService
 * 작성자          : 김상준
 * 일자            : 2024-12-27
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-27        김상준            최초 생성
 */

public interface UserService {

    int checkId(String id);

    int save(UserDto user) throws IOException;

    int edit(UserDto user, boolean fileChk) throws IOException;

    int checkPw(String token, String password);

    UserDto userInfo(String id) throws Exception;

    Map<String, Object> userMain(String token) throws Exception;

}
