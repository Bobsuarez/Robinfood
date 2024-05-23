package com.robinfood.customersbc.thirdparties.configs;

import com.robinfood.customersbc.thirdparties.components.GlobalExceptionConfiguration;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.List;

@Configuration
public class WebExceptionHandlerConfig {

    @Primary
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ErrorWebExceptionHandler errorWebExceptionHandler(
        ObjectProvider<List<ViewResolver>> viewResolversProvider,
        ServerCodecConfigurer serverCodecConfigurer
    ) {
        return new GlobalExceptionConfiguration();
    }

}
