package com.robinfood.changestatusor.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Configuration
@Data
@Validated
public class TokenToBusinessCapabilityProperties {

    @NotNull
    @Value("${token-to-business-capability.auth-key}")
    private String authKey;

    @NotNull
    @Value("${token-to-business-capability.auth-secret}")
    private String authSecret;

    @NotNull
    @Value("${token-to-business-capability.issuer}")
    private String issuer;

    @NotNull
    @Value("${token-to-business-capability.redis-id}")
    private String redisId;

    public TokenToBusinessCapabilityProperties() {
    }

    public TokenToBusinessCapabilityProperties(String authKey, String authSecret, String issuer, String redisId) {
        this.authKey = authKey;
        this.authSecret = authSecret;
        this.issuer = issuer;
        this.redisId = redisId;
    }
}
