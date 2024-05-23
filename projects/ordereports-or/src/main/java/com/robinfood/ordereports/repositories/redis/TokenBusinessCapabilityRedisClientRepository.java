package com.robinfood.ordereports.repositories.redis;

import com.robinfood.ordereports.configs.TokenToBusinessCapabilityProperties;
import com.robinfood.ordereports.entities.redis.TokenBusinessCapabilityRedisEntity;
import com.robinfood.ordereports.models.domain.TokenModel;
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
    public Optional<TokenModel> getToken() {
        try {
            log.info("Starting the token search on redis, {} ", configurationProperties);

            return tokenBusinessCapabilityRedisRepository.findById(configurationProperties.getRedisId())
                    .map(TokenBusinessCapabilityRedisEntity::getToken);
        } catch (Exception e) {
            log.error("Exception Redis : {}", e);
            return Optional.empty();
        }
    }

    @Override
    public void setToken(TokenModel token) {
        final TokenBusinessCapabilityRedisEntity tokenRedis = TokenBusinessCapabilityRedisEntity.builder()
            .tokenId(configurationProperties.getRedisId())
            .timeToLive(token.getExpiresIn())
            .token(token)
            .build();

        try {

            tokenBusinessCapabilityRedisRepository.save(tokenRedis);

            log.info("The token was successfully in redis");
        } catch (Exception e) {
            log.info("Exception Redis : {}", e);
        }
    }
}
