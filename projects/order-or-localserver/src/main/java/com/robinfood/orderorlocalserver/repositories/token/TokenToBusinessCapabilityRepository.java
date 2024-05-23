package com.robinfood.orderorlocalserver.repositories.token;

import com.robinfood.orderorlocalserver.configs.TokenToBusinessCapabilityProperties;
import com.robinfood.orderorlocalserver.entities.token.TokenModelEntity;
import com.robinfood.orderorlocalserver.entities.token.TokenRequestEntity;
import com.robinfood.orderorlocalserver.entities.token.TokenResponseEntity;
import com.robinfood.orderorlocalserver.network.SSOApi;
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

    public TokenToBusinessCapabilityRepository(SSOApi ssoApi,
                                               TokenToBusinessCapabilityProperties configurationProperties) {
        this.ssoApi = ssoApi;
        this.configurationProperties = configurationProperties;
    }

    @Override
    public TokenModelEntity get() {

        log.debug("Starting repository to get the token from sso");

        TokenRequestEntity request = buildRequest();

        log.debug("This is the request to get the service token from sso: {}",request);

        TokenResponseEntity tokenResponse = ssoApi.get(request).getBody();

        log.debug("This is the token response from sso: {}",tokenResponse);

        LocalDateTime dateNow = LocalDateTime.now();
        LocalDateTime dateToken = LocalDateTime
            .ofInstant(ofEpochSecond(tokenResponse.getResult().getExpiresIn()), ZoneId.systemDefault());

        return tokenResponse
            .toDomainWithExpirationMinute(
                Duration.between(dateNow, dateToken).toMinutes()
            );
    }

    private TokenRequestEntity buildRequest() {

        log.debug("Starting method to build the request to get the service token from sso");

        return TokenRequestEntity.toThis(
            configurationProperties.getAuthKey(),
            configurationProperties.getAuthSecret(),
            configurationProperties.getIssuer()
        );
    }
}
