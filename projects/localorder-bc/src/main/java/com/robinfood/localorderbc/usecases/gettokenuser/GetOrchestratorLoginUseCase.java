package com.robinfood.localorderbc.usecases.gettokenuser;


import com.robinfood.localorderbc.dtos.security.CredentialsDTO;
import com.robinfood.localorderbc.dtos.security.ResponseSSOLoginScopeDTO;
import com.robinfood.localorderbc.entities.token.TokenModel;
import com.robinfood.localorderbc.repositories.redis.ITokenOrchestratorRedisClientRepository;
import com.robinfood.localorderbc.repositories.sso.ISsoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetOrchestratorLoginUseCase implements IGetOrchestratorLoginUseCase {

    private final ISsoRepository ssoRepository;
    private final ITokenOrchestratorRedisClientRepository tokenBusinessCapabilityRedisClientRepository;
    private final IGetCalculateEpoch getCalculateEpoch;

    @Value("${sso.credentials.email}")
    private String email;

    @Value("${sso.credentials.password}")
    private String password;

    public GetOrchestratorLoginUseCase(ISsoRepository ssoRepository,
                                       ITokenOrchestratorRedisClientRepository
                                               tokenBusinessCapabilityRedisClientRepository,
                                       IGetCalculateEpoch getCalculateEpoch) {
        this.ssoRepository = ssoRepository;
        this.tokenBusinessCapabilityRedisClientRepository = tokenBusinessCapabilityRedisClientRepository;
        this.getCalculateEpoch = getCalculateEpoch;
    }

    public TokenModel invoke(String authorizationToken, String getterKey) {
        log.info("Starting process to save token in redis for login");

        return getTokenAndSaveInRedis(authorizationToken, getterKey);
    }

    public TokenModel getTokenAndSaveInRedis(String accessToken, String getterKey) {

        log.info("Generate login with token ´{} ", accessToken);

        CredentialsDTO credentialsDTO = CredentialsDTO.builder()
                .email(email)
                .password(password)
                .build();

        ResponseSSOLoginScopeDTO responseSSOLoginScopeDTO = ssoRepository.login(
                accessToken,
                credentialsDTO);

        TokenModel tokenModel = responseSSOLoginScopeDTO.toDomainWithExpirationMinute(
                getCalculateEpoch.minutesExpiration(responseSSOLoginScopeDTO
                        .getResult()
                        .getToken()
                        .getToken()
                        .getRefreshExpiresIn())
        );

        tokenBusinessCapabilityRedisClientRepository.setToken(tokenModel, getterKey);

        return tokenModel;

    }

}
