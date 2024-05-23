package com.robinfood.changestatusor.repository.redis;

import com.robinfood.changestatusor.config.properties.TokenToBusinessCapabilityProperties;
import com.robinfood.changestatusor.entities.redis.TokenBusinessCapabilityRedis;
import com.robinfood.changestatusor.models.domain.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class TokenBusinessCapabilityRedisClientRepository implements ITokenBusinessCapabilityRedisClientRepository {

    private final ITokenBusinessCapabilityRedisRepository tokenBusinessCapabilityRedisRepository;
    private final TokenToBusinessCapabilityProperties configurationProperties;

    public TokenBusinessCapabilityRedisClientRepository(
            ITokenBusinessCapabilityRedisRepository tokenBusinessCapabilityRedisRepository,
            TokenToBusinessCapabilityProperties configurationProperties) {
        this.tokenBusinessCapabilityRedisRepository = tokenBusinessCapabilityRedisRepository;
        this.configurationProperties = configurationProperties;
    }

    @Override
    public Optional<Token> getToken() {

        log.info("Starting the token search on redis");

        try {
            return tokenBusinessCapabilityRedisRepository.findById(configurationProperties.getRedisId())
                    .map(TokenBusinessCapabilityRedis::getToken);
        }catch (Exception e) {
            log.error("Exception Redis : {}", e);
            return Optional.empty();
        }
    }

    @Override
    public void setToken(Token token) {

        TokenBusinessCapabilityRedis tokenRedis = TokenBusinessCapabilityRedis.builder()
                .tokenId(configurationProperties.getRedisId())
                .timeToLive(token.getExpiresIn())
                .token(token)
                .build();

        try {
            tokenBusinessCapabilityRedisRepository.save(tokenRedis);
        } catch (Exception e) {
            log.info("Exception Redis : {}", e);
        }

        log.info("The token was successfully stored in redis");
    }
}
