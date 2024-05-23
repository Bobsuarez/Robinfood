package com.robinfood.storeor.usecase.gettokenbusinesscapability;


import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.repositories.redis.ITokenBusinessCapabilityRedisClientRepository;
import com.robinfood.storeor.repositories.token.ITokenToBusinessCapabilityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Implementation of IGetTokenBusinessCapabilityUseCase
 **/
@Component
@Slf4j
public class GetTokenBusinessCapabilityUseCase implements IGetTokenBusinessCapabilityUseCase {
    private final ITokenToBusinessCapabilityRepository getTokenToBusinessCapabilityRepository;
    private final ITokenBusinessCapabilityRedisClientRepository tokenBusinessCapabilityRedisClientRepository;

    public GetTokenBusinessCapabilityUseCase(
            ITokenToBusinessCapabilityRepository getTokenToBusinessCapabilityRepository,
            ITokenBusinessCapabilityRedisClientRepository tokenBusinessCapabilityRedisClientRepository
    ) {
        this.getTokenToBusinessCapabilityRepository = getTokenToBusinessCapabilityRepository;
        this.tokenBusinessCapabilityRedisClientRepository = tokenBusinessCapabilityRedisClientRepository;
    }

    @Override
    public TokenModel invoke() {
        log.info("Starting process to get token");

        return tokenBusinessCapabilityRedisClientRepository.getToken()
            .orElseGet(this::getTokenAndSaveInRedis);
    }

    /**
     * Generate service Token and
     * save into Redis
     *
     * @return Token --> Token SSO generate
     */
    private TokenModel getTokenAndSaveInRedis() {
        log.info("Starting process to save token in redis");

        var token = getTokenToBusinessCapabilityRepository.get();

        tokenBusinessCapabilityRedisClientRepository.setToken(token);

        return token;
    }

}
