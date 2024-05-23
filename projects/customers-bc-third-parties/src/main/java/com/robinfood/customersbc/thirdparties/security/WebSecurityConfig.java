package com.robinfood.customersbc.thirdparties.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.AuthorizeExchangeSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.CsrfSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.ExceptionHandlingSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.FormLoginSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.HttpBasicSpec;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
@AllArgsConstructor
@EnableWebFluxSecurity
public class WebSecurityConfig {
    private CommonAuthenticationManager authenticationManager;
    private CommonSecurityContextRepository securityContextRepository;

    private static final String[] AUTH_WHITE_LIST = {
        "/api-docs/**",
        "/v3/api-docs/**",
        "/webjars/**",
        "/swagger-ui/**",
        "/swagger-ui.html",
        "/actuator",
        "/actuator/health",
        "/actuator/refresh"
    };

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
            .exceptionHandling((ExceptionHandlingSpec exceptionHandlingSpec) ->
                exceptionHandlingSpec
                    .authenticationEntryPoint((ServerWebExchange swe, AuthenticationException e) -> Mono.error(e))
                    .accessDeniedHandler((ServerWebExchange swe, AccessDeniedException e) -> Mono.error(e))
            )
            .csrf(CsrfSpec::disable)
            .formLogin(FormLoginSpec::disable)
            .httpBasic(HttpBasicSpec::disable)
            .authenticationManager(authenticationManager)
            .securityContextRepository(securityContextRepository)
            .authorizeExchange((AuthorizeExchangeSpec authorizeExchangeSpec) ->
                authorizeExchangeSpec
                    .pathMatchers(HttpMethod.OPTIONS)
                    .permitAll()
                    .pathMatchers(AUTH_WHITE_LIST)
                    .permitAll()
                    .anyExchange()
                    .authenticated()
            )
            .build();
    }
}
