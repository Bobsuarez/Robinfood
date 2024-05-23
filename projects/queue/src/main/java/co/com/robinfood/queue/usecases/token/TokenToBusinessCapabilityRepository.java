package co.com.robinfood.queue.usecases.token;

import co.com.robinfood.queue.app.config.TokenToBusinessCapabilityProperties;
import co.com.robinfood.queue.network.SSOTokenApi;
import co.com.robinfood.queue.persistencia.entity.token.TokenModelEntity;
import co.com.robinfood.queue.persistencia.entity.token.TokenRequestEntity;
import co.com.robinfood.queue.persistencia.entity.token.TokenResponseEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.time.Instant.ofEpochSecond;

@Slf4j
@Service
@AllArgsConstructor
public class TokenToBusinessCapabilityRepository implements ITokenToBusinessCapabilityRepository {

    private final SSOTokenApi ssoApi;
    private final TokenToBusinessCapabilityProperties configurationProperties;

    @Override
    public TokenModelEntity get() {

        log.debug("Starting repository to get the token from sso");

        TokenRequestEntity request = buildRequest();

        log.debug("This is the request to get the service token from sso: {}", request);

        TokenResponseEntity tokenResponse = ssoApi.get(request)
                .getBody();

        log.debug("This is the token response from sso: {}", tokenResponse);

        LocalDateTime dateNow = LocalDateTime.now();
        LocalDateTime dateToken = LocalDateTime
                .ofInstant(ofEpochSecond(
                        tokenResponse.getResult()
                                .getExpiresIn()), ZoneId.systemDefault());

        return tokenResponse
                .toDomainWithExpirationMinute(
                        Duration.between(dateNow, dateToken)
                                .toMinutes()
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
