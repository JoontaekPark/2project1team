package org.green.frontend.service.JobNotice;

import lombok.RequiredArgsConstructor;
import org.green.frontend.dto.JobNotice.ApplyStatusRequestDto;
import org.green.frontend.dto.JobNotice.ApplyStatusResponseDto;
import org.green.frontend.dto.JobNotice.JobNoticeResponseDto;
import org.green.frontend.dto.common.CodeInfoDto;
import org.green.frontend.dto.common.UserDto;
import org.green.frontend.global.common.ApiResponse;
import org.green.frontend.utils.WebClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
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

    @Autowired
    private WebClientUtil webClientUtil;

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
    public List<CodeInfoDto> workTypeInfo() throws Exception {

        ApiResponse result = webClient.get()
                .uri("/api/v1/code-Infos?upCd=employment_type_gbn_cd")
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

    @Override
    public List<CodeInfoDto> applyInfo() throws Exception {

        ApiResponse result = webClient.get()
                .uri("/api/v1/code-Infos?upCd=apply_gbn_cd")
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

    //공고 조회
    @Override
    public JobNoticeResponseDto getJobNoticeDetail(Integer jobNoticeNum) throws Exception {
        ApiResponse<JobNoticeResponseDto> result = webClientUtil.getApi("/api/v1/job-notice?jobNoticeNum=" + jobNoticeNum, JobNoticeResponseDto.class);
        return result.getBody();
    }

    //채용공고 리스트 조회
    @Override
    public List<JobNoticeResponseDto> getJobNoticeList() throws Exception {
        ApiResponse<List> result = webClientUtil.getApi("/api/v1/job-notice-list", List.class);
            return result.getBody();
    }

    //지원현황
    @Override
    public List<ApplyStatusResponseDto> getApplyStatusList(Integer jobNoticeNum) throws Exception {
        ApiResponse<List> result = webClientUtil.getApi("/api/v1/status-list?jobNoticeNum=" + jobNoticeNum, List.class);
        return result.getBody();
    }



}
