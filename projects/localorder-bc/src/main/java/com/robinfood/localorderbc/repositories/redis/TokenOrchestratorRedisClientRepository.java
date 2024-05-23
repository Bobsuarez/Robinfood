package com.robinfood.localorderbc.repositories.redis;

import com.robinfood.localorderbc.entities.redis.TokenOrchestratorRedisEntity;
import com.robinfood.localorderbc.entities.token.TokenModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class TokenOrchestratorRedisClientRepository implements
        ITokenOrchestratorRedisClientRepository {

    @Autowired
    private final ITokenOrchestratorRedisRepository tokenBusinessCapabilityRedisRepository;

    public TokenOrchestratorRedisClientRepository(ITokenOrchestratorRedisRepository
                                                          tokenBusinessCapabilityRedisRepository) {
        this.tokenBusinessCapabilityRedisRepository = tokenBusinessCapabilityRedisRepository;
    }

    @Override
    public Optional<TokenModel> getToken(String redisId) {
        log.info("Starting the token search on redis");

        return tokenBusinessCapabilityRedisRepository.findById(redisId)
            .map(TokenOrchestratorRedisEntity::getToken);
    }

    @Override
    public void setToken(TokenModel token, String redisId) {
        var tokenRedis = TokenOrchestratorRedisEntity.builder()
            .tokenId(redisId)
            .timeToLive(token.getExpiresIn())
            .token(token)
            .build();

        tokenBusinessCapabilityRedisRepository.save(tokenRedis);

        log.info("The token was successfully stored in redis");
    }

    @Override
    public void deleteAllKeyRedis() {
        tokenBusinessCapabilityRedisRepository.deleteAll();
    }

}
