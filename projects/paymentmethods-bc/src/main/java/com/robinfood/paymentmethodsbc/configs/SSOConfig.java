package com.robinfood.paymentmethodsbc.configs;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class SSOConfig {
    private String issuer;

    private String authKey;

    private String authSecret;

    private Integer cacheTimeout;

    public SSOConfig(
        @Value("${sso.services.credentials.issuer}") String issuer,
        @Value("${sso.services.credentials.key}") String authKey,
        @Value("${sso.services.credentials.secret}") String authSecret,
        @Value("${sso.services.cache.timeout}") Integer cacheTimeout
    ) {
        this.issuer = issuer;
        this.authKey = authKey;
        this.authSecret = authSecret;
        this.cacheTimeout = cacheTimeout;
    }
}
