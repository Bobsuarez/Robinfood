package com.robinfood.ordereports.configs;

import com.robinfood.app.library.middleware.ProcessJwtMiddleware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtAuthorizationFilter {

    @Value("${jwt-token-prefix}")
    private String jwtTokenPrefix;

    @Value("${jwt.token.secret}")
    private String jwtTokenSecret;

    @Value("${jwt-token-aud}")
    private String jwtTokenAud;

    @Value("${jwt-token-mod}")
    private String jwtTokenMod;

    @Bean
    public ProcessJwtMiddleware processJwtMiddleware() {
        return new ProcessJwtMiddleware(jwtTokenPrefix, jwtTokenSecret, jwtTokenAud, jwtTokenMod);
    }
}
