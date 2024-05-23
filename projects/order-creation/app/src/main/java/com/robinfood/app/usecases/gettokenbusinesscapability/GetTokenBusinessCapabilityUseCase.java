package com.robinfood.app.usecases.gettokenbusinesscapability;

import com.robinfood.core.models.domain.Token;
import com.robinfood.repository.redis.ITokenBusinessCapabilityRedisClientRepository;
import com.robinfood.repository.token.ITokenToBusinessCapabilityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
public class GetTokenBusinessCapabilityUseCase implements IGetTokenBusinessCapabilityUseCase {

    private final ITokenToBusinessCapabilityRepository getTokenToBusinessCapabilityRepository;
    private final ITokenBusinessCapabilityRedisClientRepository tokenBusinessCapabilityRedisClientRepository;

    @Override
    public Token invoke() {

        log.info("Starting process to get token");

        return tokenBusinessCapabilityRedisClientRepository.getToken()
            .orElseGet(this::getTokenAndSaveInRedis);
    }

    private Token getTokenAndSaveInRedis() {
        log.info("Starting process to save token in redis");

        final Token token = getTokenToBusinessCapabilityRepository.get();

        tokenBusinessCapabilityRedisClientRepository.setToken(token);

        return token;
    }

}
