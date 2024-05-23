package com.robinfood.storeor.repositories.redis;

import com.robinfood.storeor.configs.TokenToBusinessCapabilityProperties;
import com.robinfood.storeor.entities.redis.TokenBusinessCapabilityRedisEntity;
import com.robinfood.storeor.entities.token.TokenModel;
import lombok.AllArgsConstructor;
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
            TokenToBusinessCapabilityProperties configurationProperties
    ) {
        this.tokenBusinessCapabilityRedisRepository = tokenBusinessCapabilityRedisRepository;
        this.configurationProperties = configurationProperties;
    }

    @Override
    public Optional<TokenModel> getToken() {
        log.info("Starting the token search on redis");

        return tokenBusinessCapabilityRedisRepository.findById(configurationProperties.getRedisId())
            .map(TokenBusinessCapabilityRedisEntity::getToken);
    }

    @Override
    public void setToken(TokenModel token) {
        var tokenRedis = TokenBusinessCapabilityRedisEntity.builder()
            .tokenId(configurationProperties.getRedisId())
            .timeToLive(token.getExpiresIn())
            .token(token)
            .build();

        tokenBusinessCapabilityRedisRepository.save(tokenRedis);

        log.info("The token was successfully stored in redis");
    }

}
