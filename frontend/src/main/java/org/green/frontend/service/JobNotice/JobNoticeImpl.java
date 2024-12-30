package org.green.frontend.service.JobNotice;

import lombok.RequiredArgsConstructor;
import org.green.frontend.dto.common.CodeInfoDto;
import org.green.frontend.global.common.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Created on 2024-12-30 by 최윤서
 */
@Service
public class JobNoticeImpl implements JobNoticeService {

    @Autowired
    private WebClient webClient;

    @Override
    public List<CodeInfoDto> careerInfo() throws Exception {

        ApiResponse result = webClient.get()
                .uri("/api/v1/code-Infos?upCd=job_notice_career_gbn_cd")
                .retrieve()
                .bodyToMono(ApiResponse.class)
                .onErrorResume(ex -> {
                    // 기본 응답이나 오류 반환
                    return Mono.just(new ApiResponse<>(ApiResponse.ApiStatus.ERROR, ex.getMessage()));
                })
                .block();

        return (List<CodeInfoDto>) result.getBody();


    }

    @Override
    public List<CodeInfoDto> educationInfo() throws Exception {

        ApiResponse result = webClient.get()
                .uri("/api/v1/code-Infos?upCd=job_notice_education_gbn_cd")
                .retrieve()
                .bodyToMono(ApiResponse.class)
                .onErrorResume(ex -> {
                    // 기본 응답이나 오류 반환
                    return Mono.just(new ApiResponse<>(ApiResponse.ApiStatus.ERROR, ex.getMessage()));
                })
                .block();
        System.out.println("API Response: " + result);

        return (List<CodeInfoDto>) result.getBody();


    }

    @Override
    public List<CodeInfoDto> stackInfo() throws Exception {

        ApiResponse result = webClient.get()
                .uri("/api/v1/code-infos/subcode?upCd=stack_cd")
                .retrieve()
                .bodyToMono(ApiResponse.class)
                .onErrorResume(ex -> {
                    // 기본 응답이나 오류 반환
                    return Mono.just(new ApiResponse<>(ApiResponse.ApiStatus.ERROR, ex.getMessage()));
                })
                .block();
        System.out.println("API Response: " + result);

        return (List<CodeInfoDto>) result.getBody();

    }


}
