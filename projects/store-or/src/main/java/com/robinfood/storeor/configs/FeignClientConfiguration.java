package com.robinfood.storeor.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import feign.Logger;

@Configuration
public class FeignClientConfiguration {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
