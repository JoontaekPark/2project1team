package org.green.frontend.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
@Slf4j
public class WebClientConfig implements WebMvcConfigurer {

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl("http://192.168.0.225:8080") // 백엔드 URL
                .defaultHeader("Content-Type", "application/json") // 기본 Content-Type
                .filter(addAuthHeaderFilter()) // JWT 추가 필터
                .build();
    }

    private ExchangeFilterFunction addAuthHeaderFilter() {
        return (request, next) -> {
            String token = getJwtFromSessionOrRequest();

            if (token != null) {
                ClientRequest newRequest = ClientRequest.from(request)
                        .header("Authorization", token)
                        .build();
                return next.exchange(newRequest);
            }

            return next.exchange(request);
        };
    }

    private String getJwtFromSessionOrRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("Authorization".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    @Value("${file.upload-dir}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //System.getProperty("user.dir")는 Java 애플리케이션이 실행되는 현재 작업 디렉토리(working directory)의 절대 경로를 반환
        String projectPath = System.getProperty("user.dir");

        // 절대 경로 생성 (static 폴더 밖)
        String absolutePath = projectPath + File.separator + "frontend" + File.separator + "uploads" + File.separator;

        log.info("File access path configured: file:///{}", absolutePath);
        //클라이언트가 /uploads/ 로 시작하는 URL로 요청할 때 이 핸들러가 동작
        //이 핸들러는 실제 파일시스템의 uploads 폴더로 매핑
        registry.addResourceHandler("/uploads/**")
                //file:/// 프로토콜을 사용하여 로컬 파일시스템에 접근
                //absolutePath 는 파일 저장 경로
                // 첫 번째 /: 프로토콜과 호스트 구분
                // 두 번째, 세 번째 //: 호스트가 없음을 나타냄 (로컬 파일시스템)
                .addResourceLocations("file:///" + absolutePath);

    }

}
