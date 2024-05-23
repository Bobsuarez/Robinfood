package com.robinfood.orderorlocalserver.usecases.gettokenbusinesscapability;

import com.robinfood.orderorlocalserver.entities.token.TokenModelEntity;
import com.robinfood.orderorlocalserver.repositories.redis.ITokenBusinessCapabilityRedisClientRepository;
import com.robinfood.orderorlocalserver.repositories.token.ITokenToBusinessCapabilityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GetTokenBusinessCapabilityUseCase implements IGetTokenBusinessCapabilityUseCase {

    private final ITokenToBusinessCapabilityRepository getTokenToBusinessCapabilityRepository;
    private final ITokenBusinessCapabilityRedisClientRepository tokenBusinessCapabilityRedisClientRepository;

    public GetTokenBusinessCapabilityUseCase(
            ITokenToBusinessCapabilityRepository getTokenToBusinessCapabilityRepository,
            ITokenBusinessCapabilityRedisClientRepository tokenBusinessCapabilityRedisClientRepository) {
        this.getTokenToBusinessCapabilityRepository = getTokenToBusinessCapabilityRepository;
        this.tokenBusinessCapabilityRedisClientRepository = tokenBusinessCapabilityRedisClientRepository;
    }

    @Override
    public TokenModelEntity invoke() {

        log.debug("Starting use case to get the business token saved in redis");

        return tokenBusinessCapabilityRedisClientRepository.getToken()
                .orElseGet(this::getTokenAndSaveInRedis);
    }


    private TokenModelEntity getTokenAndSaveInRedis() {

        log.debug("Starting method to get the token from sso (token was not found in redis) and save token in redis");

        TokenModelEntity token = getTokenToBusinessCapabilityRepository.get();

        log.debug("This is the sso token received from repository: {}" ,token);

        tokenBusinessCapabilityRedisClientRepository.setToken(token);

        return token;
    }

}
