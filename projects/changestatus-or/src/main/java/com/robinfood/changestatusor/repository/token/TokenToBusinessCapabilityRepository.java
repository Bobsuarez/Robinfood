package com.robinfood.changestatusor.repository.token;

import com.robinfood.changestatusor.config.network.api.token.TokenToBusinessCapabilityAPI;
import com.robinfood.changestatusor.config.properties.TokenToBusinessCapabilityProperties;
import com.robinfood.changestatusor.enums.TransactionCreationErrors;
import com.robinfood.changestatusor.exceptions.TransactionCreationException;
import com.robinfood.changestatusor.models.domain.Token;
import com.robinfood.changestatusor.models.retrofit.TokenRequest;
import com.robinfood.changestatusor.models.retrofit.TokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.time.Instant.ofEpochSecond;

@Component
@Slf4j
public class TokenToBusinessCapabilityRepository implements ITokenToBusinessCapabilityRepository {

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
        log.info("Init get token services request: {}", request);
        TokenResponse result = tokenToBusinessCapabilityApi.get(request);

        log.info("result get token: {}", result);

        if(result.getStatus().equals(HttpStatus.OK.toString())){
            throw new TransactionCreationException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error while calling SSO service",
                    TransactionCreationErrors.SSO_ERROR
            );
        }

        var response = result;

        log.info("response get token: {}", response);

        var dateNow = LocalDateTime.now();
        var dateToken = LocalDateTime
                .ofInstant(ofEpochSecond(response.getResult().getExpires_in()), ZoneId.systemDefault());

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
