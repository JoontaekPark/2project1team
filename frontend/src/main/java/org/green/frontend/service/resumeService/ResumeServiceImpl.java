package org.green.frontend.service.resumeService;

import lombok.RequiredArgsConstructor;
import org.green.frontend.dto.resume.ResumeDto;
import org.green.frontend.global.common.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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


 @Override
 public ApiResponse<Object> regResume(ResumeDto resumeDto) {


     ApiResponse result = webClient.post()
             .uri("/resume/regProc")
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

