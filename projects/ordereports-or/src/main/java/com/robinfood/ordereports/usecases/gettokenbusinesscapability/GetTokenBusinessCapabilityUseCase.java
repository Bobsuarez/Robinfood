package com.robinfood.ordereports.usecases.gettokenbusinesscapability;


import com.robinfood.ordereports.models.domain.TokenModel;
import com.robinfood.ordereports.repositories.redis.ITokenBusinessCapabilityRedisClientRepository;
import com.robinfood.ordereports.repositories.token.ITokenToBusinessCapabilityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GetTokenBusinessCapabilityUseCase implements IGetTokenBusinessCapabilityUseCase {

    /**
     * Implementation of IGetTokenBusinessCapabilityUseCase
     **/
    private final ITokenToBusinessCapabilityRepository getTokenToBusinessCapabilityRepository;
    private final ITokenBusinessCapabilityRedisClientRepository tokenBusinessCapabilityRedisClientRepository;

    public GetTokenBusinessCapabilityUseCase(
            ITokenToBusinessCapabilityRepository getTokenToBusinessCapabilityRepository,
            ITokenBusinessCapabilityRedisClientRepository tokenBusinessCapabilityRedisClientRepository) {
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

        log.info("get token the redis...");

        tokenBusinessCapabilityRedisClientRepository.setToken(token);

        return token;
    }

}

