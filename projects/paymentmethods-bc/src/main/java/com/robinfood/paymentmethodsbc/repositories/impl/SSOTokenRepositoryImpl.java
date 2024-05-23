package com.robinfood.paymentmethodsbc.repositories.impl;

import com.robinfood.paymentmethodsbc.configs.SSOConfig;
import com.robinfood.paymentmethodsbc.constants.RedisConstants;
import com.robinfood.paymentmethodsbc.dto.sso.SSOGenericResponseDTO;
import com.robinfood.paymentmethodsbc.dto.sso.SSOServiceTokenDTO;
import com.robinfood.paymentmethodsbc.dto.sso.SSOServiceTokenRequestDTO;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.repositories.RedisRepository;
import com.robinfood.paymentmethodsbc.repositories.SSOTokenRepository;
import com.robinfood.paymentmethodsbc.security.sso.SSOClient;
import feign.FeignException;
import java.time.Duration;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class SSOTokenRepositoryImpl implements SSOTokenRepository {
    private final SSOClient ssoHttpClient;
    private final SSOConfig ssoConfig;
    private final RedisRepository redisRepository;

    SSOTokenRepositoryImpl(
        SSOClient ssoHttpClient,
        SSOConfig ssoConfig,
        RedisRepository redisRepository
    ) {
        this.ssoHttpClient = ssoHttpClient;
        this.ssoConfig = ssoConfig;
        this.redisRepository = redisRepository;
    }

    @Override
    public String getServiceToken(boolean force) throws BaseException {
        if (!force) {
            log.info("Obtaining SSO token from cache");
            Optional<String> optionalToken = redisRepository.get(
                RedisConstants.PM_REDIS_SSO_SERVICE_TOKEN_KEY
            );

            if (optionalToken.isPresent()) {
                log.info("SSO token successfully obtained from cache");
                return optionalToken.get();
            }
        }

        SSOGenericResponseDTO<SSOServiceTokenDTO> result;
        try {
            log.info("Obtaining SSO token from service");
            result =
                ssoHttpClient.getServiceToken(
                    SSOServiceTokenRequestDTO
                        .builder()
                        .authKey(ssoConfig.getAuthKey())
                        .authSecret(ssoConfig.getAuthSecret())
                        .issuer(ssoConfig.getIssuer())
                        .build()
                );
            log.info("SSO token successfully obtained from service");
        } catch (FeignException e) {
            log.error("Error getting SSO service token", e);
            throw new BaseException("Error obteniendo token de servicio");
        }
        SSOServiceTokenDTO accessToken = result.getResult();

        redisRepository.set(
            RedisConstants.PM_REDIS_SSO_SERVICE_TOKEN_KEY,
            accessToken.getAccessToken(),
            Duration.ofSeconds(ssoConfig.getCacheTimeout())
        );

        return accessToken.getAccessToken();
    }
}
