package com.robinfood.core.interceptors;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@AllArgsConstructor
@Component
public class RestControllerInterceptorAppConfig implements WebMvcConfigurer {

    private final RestControllerInterceptor restControllerInterceptor;

    @Override
    public void addInterceptors(@NotNull InterceptorRegistry registry) {
        registry.addInterceptor(restControllerInterceptor);
    }
}
