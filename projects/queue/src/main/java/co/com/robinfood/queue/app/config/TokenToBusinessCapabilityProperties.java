package co.com.robinfood.queue.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "token-to-business-capability")
@Data
@Validated
public class TokenToBusinessCapabilityProperties {

    private String authKey;

    private String authSecret;

    private String issuer;

    private String redisId;

    public TokenToBusinessCapabilityProperties() {
        // this constructor is empty because it is a configuration class
    }
}
