package org.green.frontend.service.resumeService;

import lombok.RequiredArgsConstructor;
import org.green.frontend.dto.common.CodeInfoDto;
import org.green.frontend.dto.common.UserDto;
import org.green.frontend.dto.company.CompanyDto;
import org.green.frontend.dto.resume.ResumeDto;
import org.green.frontend.dto.resume.ResumeInfoAll2Dto;
import org.green.frontend.global.common.ApiResponse;
import org.green.frontend.utils.WebClientUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * packageName    : org.green.frontend.service.resumeService
 * fileName       : ResumeServiceImpl
 * author         : 박준택
 * date           : 2024-12-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-27        박준택       최초 생성
 */
@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

 private final WebClient webClient;
 private final WebClientUtil webClientUtil;

    @Override
    public List<CodeInfoDto> certResult() throws Exception {

        ApiResponse result = webClient.get()
                .uri("/api/v1/code-Infos?upCd=resume_certs_gbn_cd")
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


    @Override
    public List<CodeInfoDto> educationLevel() throws Exception {

        ApiResponse result = webClient.get()
                .uri("/api/v1/code-Infos?upCd=resume_education_gbn_cd")
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

    @Override
    public List<CodeInfoDto> stacks() throws Exception {
        ApiResponse result = webClient.get()
                .uri("/api/v1/code-Infos?upCd=resume_education_gbn_cd")
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
    @Override
    public List<CodeInfoDto> resumeMilitaryGbnCd() throws Exception {

        ApiResponse result = webClient.get()
                .uri("/api/v1/code-Infos?upCd=resume_military_gbn_cd")
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

    @Override
    public List<CodeInfoDto> resumeMilitaryRankGbnCd() throws Exception {

        ApiResponse result = webClient.get()
                .uri("/api/v1/code-Infos?upCd=resume_military_rank_gbn_cd")
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

    @Override
    public List<CodeInfoDto> resumeMilitaryTypeGbnCd() throws Exception {

        ApiResponse result = webClient.get()
                .uri("/api/v1/code-Infos?upCd=resume_military_type_gbn_cd")
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

    @Override
    public List<CodeInfoDto> resumeMilitaryFinishGbnCd() throws Exception {

        ApiResponse result = webClient.get()
                .uri("/api/v1/code-Infos?upCd=resume_military_finish_gbn_cd")
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


    @Override
    public UserDto getUser() throws Exception {

        ApiResponse<UserDto> response = webClientUtil.getApi("/resume/getLoginUser", UserDto.class);
//뒤에꺼 매개변수에 (url,반환타입)
        return response.getBody();
    }


}


