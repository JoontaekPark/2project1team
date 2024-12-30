package org.green.frontend.service.common;

import lombok.RequiredArgsConstructor;
import org.green.frontend.dto.common.CodeInfoDto;
import org.green.frontend.dto.common.UserDto;
import org.green.frontend.global.common.ApiResponse;
import org.green.frontend.utils.WebClientUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 패키지명        : org.green.frontend.service.common
 * 파일명          : UserServiceImpl
 * 작성자          : 김상준
 * 일자            : 2024-12-27
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2024-12-27        김상준            최초 생성
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final WebClient webClient;
    private final WebClientUtil webClientUtil;

    @Override
    public List<CodeInfoDto> genderInfo() throws Exception {
        ApiResponse<List> response = webClientUtil.getApi("/api/v1/code-Infos?upCd=gender_cd", List.class);
        return response.getBody();     }

    @Override
    public UserDto userInfo() throws Exception {

        ApiResponse<UserDto> result = webClientUtil.getApi("/api/v1/user-info", UserDto.class);
        return result.getBody();
    }
}
