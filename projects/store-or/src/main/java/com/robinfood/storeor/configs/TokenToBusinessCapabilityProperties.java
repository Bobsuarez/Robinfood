package com.robinfood.storeor.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;


@Configuration
@ConfigurationProperties(prefix = "token-to-business-capability")
@Data
@Validated
public class TokenToBusinessCapabilityProperties {

    @NotNull
    private String authKey;

    @NotNull
    private String authSecret;

    @NotNull
    private String issuer;

    @NotNull
    private String redisId;

}
