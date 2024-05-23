package com.robinfood.repository.redis;

import com.robinfood.core.entities.redis.TokenBusinessCapabilityRedisEntity;
import com.robinfood.core.models.domain.TokenModel;
import com.robinfood.repository.config.TokenToBusinessCapabilityProperties;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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

        log.info("Starting the token search on redis");

        try {

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
        } catch (Exception e) {

            log.info("Exception Redis : {}", e);
        }

        log.info("The token was successfully stored in redis");
    }
}
