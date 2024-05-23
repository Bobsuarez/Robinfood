package com.robinfood.orderorlocalserver.repositories.redis;

import com.robinfood.orderorlocalserver.configs.TokenToBusinessCapabilityProperties;
import com.robinfood.orderorlocalserver.entities.TokenBusinessCapabilityRedisEntity;
import com.robinfood.orderorlocalserver.entities.token.TokenModelEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class TokenBusinessCapabilityRedisClientRepository implements
        ITokenBusinessCapabilityRedisClientRepository {

    private final ITokenBusinessCapabilityRedisRepository tokenBusinessCapabilityRedisRepository;
    private final TokenToBusinessCapabilityProperties configurationProperties;

    public TokenBusinessCapabilityRedisClientRepository(
            ITokenBusinessCapabilityRedisRepository tokenBusinessCapabilityRedisRepository,
            TokenToBusinessCapabilityProperties configurationProperties) {
        this.tokenBusinessCapabilityRedisRepository = tokenBusinessCapabilityRedisRepository;
        this.configurationProperties = configurationProperties;
    }

    @Override
    public Optional<TokenModelEntity> getToken() {

        log.debug("Starting redis client repository to search the token");

        return tokenBusinessCapabilityRedisRepository.findById(configurationProperties.getRedisId())
                .map(TokenBusinessCapabilityRedisEntity::getToken);
    }

    @Override
    public void setToken(TokenModelEntity token) {

        log.debug("Starting redis client repository to save the token received from sso");

        var tokenRedis = TokenBusinessCapabilityRedisEntity.builder()
            .tokenId(configurationProperties.getRedisId())
            .timeToLive(token.getExpiresIn())
            .token(token)
            .build();

        log.debug("This is the token to save in redis {}", tokenRedis);

        tokenBusinessCapabilityRedisRepository.save(tokenRedis);

        log.debug("The sso token was successfully stored in redis");
    }

}
