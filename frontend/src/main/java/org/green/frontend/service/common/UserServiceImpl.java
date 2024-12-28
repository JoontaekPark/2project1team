package org.green.frontend.service.common;

import lombok.RequiredArgsConstructor;
import org.green.frontend.dto.common.CodeInfoDto;
import org.green.frontend.global.common.ApiResponse;
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

    @Override
    public List<CodeInfoDto> genderInfo() throws Exception {

        ApiResponse result = webClient.get()
                .uri("/api/v1/code-Infos?upCd=gender_cd")
                .retrieve()
                .bodyToMono(ApiResponse.class)
                .onErrorResume(ex -> {
                    // 기본 응답이나 오류 반환
                    return Mono.just(new ApiResponse<>(ApiResponse.ApiStatus.ERROR, ex.getMessage()));
                })
                .block();

        if (result.getStatus().equals(ApiResponse.ApiStatus.ERROR)) {
            throw new Exception("오류발생!!");
        }

        return (List<CodeInfoDto>) result.getBody();
    }
}
