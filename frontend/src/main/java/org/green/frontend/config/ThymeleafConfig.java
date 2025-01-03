package org.green.frontend.config;

import org.green.frontend.utils.DateUtil;
import org.green.frontend.utils.NumberUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 패키지명        : org.green.frontend.config
 * 파일명          : ThymeleafConfig
 * 작성자          : 김상준
 * 일자            : 2025-01-01
 * 내용            :
 * ===========================================================
 * 일자              작성자             메모
 * -----------------------------------------------------------
 * 2025-01-01        김상준            최초 생성
 */

@Configuration
public class ThymeleafConfig {

    @Bean
    public NumberUtil numberUtil() {
        return new NumberUtil();
    }

    @Bean
    public DateUtil dateUtil() {
        return new DateUtil();
    }
}
