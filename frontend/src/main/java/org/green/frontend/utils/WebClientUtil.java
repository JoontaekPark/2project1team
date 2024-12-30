package org.green.frontend.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.green.frontend.global.common.ApiResponse;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class WebClientUtil {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public <T> ApiResponse<T> getApi(String uri, Class<T> responseType) throws Exception {
        return executeRequest(uri, HttpMethod.GET, null, responseType);
    }

    public <T> ApiResponse<T> postApi(String uri, Object body, Class<T> responseType) throws Exception {
        return executeRequest(uri, HttpMethod.POST, body, responseType);
    }

    public <T> ApiResponse<T> putApi(String uri, Object body, Class<T> responseType) throws Exception {
        return executeRequest(uri, HttpMethod.PUT, body, responseType);
    }

    public <T> ApiResponse<T> deleteApi(String uri, Class<T> responseType) throws Exception {
        return executeRequest(uri, HttpMethod.DELETE, null, responseType);
    }

    private <T> ApiResponse<T> executeRequest(String uri, HttpMethod method, Object body, Class<T> responseType) throws Exception {
        WebClient.RequestBodySpec requestSpec = webClient.method(method).uri(uri);

        if (body != null) {
            requestSpec.bodyValue(body);
        }

        ApiResponse<Object> result = requestSpec.retrieve()
                .bodyToMono(ApiResponse.class)
                .onErrorResume(ex -> Mono.just(new ApiResponse<>(ApiResponse.ApiStatus.ERROR, ex.getMessage())))
                .block();

        if (result.getStatus().equals(ApiResponse.ApiStatus.ERROR)) {
            throw new Exception("오류발생: " + result.getBody());
        }

        // 응답 데이터를 제네릭 타입으로 변환
        T mappedBody = objectMapper.convertValue(result.getBody(), responseType);
        return new ApiResponse<>(ApiResponse.ApiStatus.SUCCESS, mappedBody);
    }
}
