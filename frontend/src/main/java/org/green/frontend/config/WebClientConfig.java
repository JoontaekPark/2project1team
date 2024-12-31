package org.green.frontend.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8080") // 백엔드 URL
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
}
