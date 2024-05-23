package com.robinfood.repository.token;

import static com.robinfood.core.extensions.NetworkExtensionsKt.safeAPICall;
import static java.time.Instant.ofEpochSecond;

import com.robinfood.core.enums.Result;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.core.models.retrofit.TokenRequest;
import com.robinfood.core.models.retrofit.TokenResponse;
import com.robinfood.network.api.SSOApi;
import com.robinfood.repository.config.TokenToBusinessCapabilityProperties;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
    public TokenModel get() {
        var request = buildRequest();

        var response = ((Result.Success<TokenResponse>) safeAPICall(
                ssoApi.get(request)))
                .getData();

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
