package com.robinfood.core.interceptors;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class RestControllerInterceptorAppConfig implements WebMvcConfigurer {

    private final RestControllerInterceptor restControllerInterceptor;

    public RestControllerInterceptorAppConfig(RestControllerInterceptor restControllerInterceptor) {
        this.restControllerInterceptor = restControllerInterceptor;
    }

    @Override
    public void addInterceptors(@NotNull InterceptorRegistry registry) {
        registry.addInterceptor(restControllerInterceptor);
    }
}
