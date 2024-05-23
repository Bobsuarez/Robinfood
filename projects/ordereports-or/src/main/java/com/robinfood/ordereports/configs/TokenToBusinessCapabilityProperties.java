package com.robinfood.ordereports.configs;

import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

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

    public TokenToBusinessCapabilityProperties() {
        // this constructor is empty because it is a configuration class
    }
}
