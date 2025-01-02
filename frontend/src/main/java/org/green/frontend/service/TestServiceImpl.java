package org.green.frontend.service;

import lombok.RequiredArgsConstructor;
import org.green.frontend.global.common.ApiResponse;
import org.green.frontend.global.common.ErrorResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final WebClient webClient;

    public ApiResponse<Object> test() {

        ApiResponse result = webClient.get()
                .uri("/api/v1/test")
                .retrieve()
                .bodyToMono(ApiResponse.class)
                .onErrorResume(ex -> {
                    // 기본 응답이나 오류 반환
                    return Mono.just(new ApiResponse<>(ApiResponse.ApiStatus.ERROR, ex.getMessage()));
                })
                .block();




        return result;
    }

}
