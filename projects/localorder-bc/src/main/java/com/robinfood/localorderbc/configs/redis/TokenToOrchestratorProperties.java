package com.robinfood.localorderbc.configs.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Configuration
@ConfigurationProperties(prefix = "token-to-tokenuser")
@Data
@Validated
public class TokenToOrchestratorProperties {

    @NotNull
    private String issuer;

    @NotNull
    private String redisId;

}
