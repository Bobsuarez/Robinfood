package com.robinfood.localorderbc.usecases.gettokenuser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.localorderbc.dtos.security.CredentialsAuthenticationDTO;
import com.robinfood.localorderbc.dtos.security.PayloadDataDTO;
import com.robinfood.localorderbc.dtos.security.ResponseSSOAuthDTO;
import com.robinfood.localorderbc.entities.token.TokenModel;
import com.robinfood.localorderbc.repositories.redis.ITokenOrchestratorRedisClientRepository;
import com.robinfood.localorderbc.repositories.sso.ISsoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@Slf4j
public class GetOrchestratorAuthUseCase implements IGetOrchestratorAuthUseCase {

    private final ISsoRepository ssoRepository;
    private final ITokenOrchestratorRedisClientRepository tokenBusinessCapabilityRedisClientRepository;
    private final IGetCalculateEpoch getCalculateEpoch;

    @Value("${token-to-tokenuser.issuer}")
    private String issuer;

    public GetOrchestratorAuthUseCase(ISsoRepository ssoRepository,
                                      ITokenOrchestratorRedisClientRepository
                                              tokenBusinessCapabilityRedisClientRepository,
                                      IGetCalculateEpoch getCalculateEpoch) {
        this.ssoRepository = ssoRepository;
        this.tokenBusinessCapabilityRedisClientRepository = tokenBusinessCapabilityRedisClientRepository;
        this.getCalculateEpoch = getCalculateEpoch;
    }

    public TokenModel invoke(String scope, String authorizationToken, String getterKey) {
        log.info("Starting process to save token in redis");

        return getTokenAndSaveInRedis(scope, authorizationToken, getterKey);
    }

    private TokenModel getTokenAndSaveInRedis(String scope, String authorizationToken, String getterKey) {

        CredentialsAuthenticationDTO credentialsAuthenticationDTO = CredentialsAuthenticationDTO
                .builder()
                .authorizationToken(authorizationToken)
                .issuer(issuer)
                .scope(scope)
                .build();

        ResponseSSOAuthDTO responseSSOAuthDTO = ssoRepository.auth(credentialsAuthenticationDTO);

        Long dateExpiration = extractExp(responseSSOAuthDTO.getResult().getAccessToken());

        TokenModel tokenModel = null;
        if (dateExpiration != null) {
            tokenModel = responseSSOAuthDTO.toDomainWithExpirationMinute(
                    getCalculateEpoch.minutesExpiration(dateExpiration)
            );
            tokenBusinessCapabilityRedisClientRepository.setToken(tokenModel, getterKey);
        }

        return tokenModel;

    }

    private Long extractExp(String tokenString){

        Long exp = null;
        try {
            String[] chunks = tokenString.split("\\.");
            String payloadJwt = chunks[1];
            String payloadReady = new String(Base64.getUrlDecoder().decode(payloadJwt), StandardCharsets.UTF_8);
            
            ObjectMapper objectMapper = new ObjectMapper();
            PayloadDataDTO payloadDataDTO = objectMapper.readValue(payloadReady, PayloadDataDTO.class);

            exp = payloadDataDTO.getExp();
        }catch (JsonProcessingException|ArrayIndexOutOfBoundsException e  ){
            log.error("Error in convert payloadDataDTO {} ", e);
        }

        return exp;
    }

}
