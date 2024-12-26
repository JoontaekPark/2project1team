package org.green.frontend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(WebClient.Builder builder) {

        return builder.baseUrl("http://localhost:8080")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

}