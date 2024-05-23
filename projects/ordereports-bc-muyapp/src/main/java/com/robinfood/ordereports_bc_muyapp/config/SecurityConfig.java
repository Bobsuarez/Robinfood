package com.robinfood.ordereports_bc_muyapp.config;

import com.robinfood.app.library.middleware.ProcessJwtMiddleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final ProcessJwtMiddleware processJwtMiddleware;

    @Autowired
    public SecurityConfig(ProcessJwtMiddleware processJwtMiddleware) {
        this.processJwtMiddleware = processJwtMiddleware;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest()
                        .authenticated()
                )
                .addFilterBefore(processJwtMiddleware, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
