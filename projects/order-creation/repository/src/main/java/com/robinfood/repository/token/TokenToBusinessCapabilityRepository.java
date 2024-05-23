package com.robinfood.repository.token;

import static com.robinfood.core.extensions.NetworkExtensionsKt.safeAPICall;
import static java.time.Instant.ofEpochSecond;

import com.robinfood.core.enums.Result;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.models.domain.Token;
import com.robinfood.core.models.retrofit.TokenRequest;
import com.robinfood.core.models.retrofit.TokenResponse;
import com.robinfood.network.api.TokenToBusinessCapabilityAPI;
import com.robinfood.repository.config.properties.TokenToBusinessCapabilityProperties;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TokenToBusinessCapabilityRepository implements
    ITokenToBusinessCapabilityRepository {

    private final TokenToBusinessCapabilityAPI tokenToBusinessCapabilityApi;
    private final TokenToBusinessCapabilityProperties configurationProperties;

    public TokenToBusinessCapabilityRepository(
            TokenToBusinessCapabilityAPI tokenToBusinessCapabilityApi,
            TokenToBusinessCapabilityProperties configurationProperties) {
        this.tokenToBusinessCapabilityApi = tokenToBusinessCapabilityApi;
        this.configurationProperties = configurationProperties;
    }

    @Override
    public Token get() {
        var request = buildRequest();

        var result = safeAPICall(tokenToBusinessCapabilityApi.get(request));

        if (result instanceof Result.Error) {
            throw new TransactionCreationException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error while calling SSO service",
                    TransactionCreationErrors.SSO_ERROR
            );
        }

        var response = ((Result.Success<TokenResponse>) result).getData();

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
