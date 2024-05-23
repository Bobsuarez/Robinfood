package com.robinfood.storeor.repositories.token;

import com.robinfood.storeor.configs.TokenToBusinessCapabilityProperties;
import com.robinfood.storeor.configs.apis.SSOApi;
import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.entities.token.TokenRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.time.Instant.ofEpochSecond;

@Component
@Slf4j
public class TokenToBusinessCapabilityRepository implements
        ITokenToBusinessCapabilityRepository {

    private final SSOApi ssoApi;
    private final TokenToBusinessCapabilityProperties configurationProperties;

    public TokenToBusinessCapabilityRepository(
            SSOApi ssoApi,
            TokenToBusinessCapabilityProperties configurationProperties
    ) {
        this.ssoApi = ssoApi;
        this.configurationProperties = configurationProperties;
    }

    @Override
    public TokenModel get() {

        var request = buildRequest();

        var response = ssoApi.get(request);

        var dateNow = LocalDateTime.now();
        var dateToken = LocalDateTime
            .ofInstant(ofEpochSecond(response.getResult().getExpiresIn()), ZoneId.systemDefault());

        return response
            .toDomainWithExpirationMinute(
                Duration.between(dateNow, dateToken).toMinutes()
            );
    }

    private TokenRequest buildRequest() {
        log.info("Building request to get the service token from SSO");

        return TokenRequest.toThis(
            configurationProperties.getAuthKey(),
            configurationProperties.getAuthSecret(),
            configurationProperties.getIssuer()
        );
    }

}
