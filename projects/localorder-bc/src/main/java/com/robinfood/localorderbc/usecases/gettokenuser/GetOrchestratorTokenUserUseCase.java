package com.robinfood.localorderbc.usecases.gettokenuser;

import com.robinfood.localorderbc.dtos.security.CredentialsWithScopesPermissionDTO;
import com.robinfood.localorderbc.dtos.security.ResponseSSOAuthDTO;
import com.robinfood.localorderbc.entities.token.TokenModel;
import com.robinfood.localorderbc.repositories.redis.ITokenOrchestratorRedisClientRepository;
import com.robinfood.localorderbc.repositories.sso.ISsoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.robinfood.localorderbc.configs.constants.APIConstants.CHAR_COMMA;
import static com.robinfood.localorderbc.configs.constants.APIConstants.STR_LOGIN_SCOPE;
import static com.robinfood.localorderbc.configs.constants.APIConstants.STR_POSV2_ACCESS_TOKEN;
import static com.robinfood.localorderbc.configs.constants.APIConstants.STR_POSV2_ACCESS_TOKEN_LOGIN;
import static com.robinfood.localorderbc.configs.constants.APIConstants.STR_POSV2_REFRESH_TOKEN;

@Service
@Slf4j
public class GetOrchestratorTokenUserUseCase implements IGetOrchestratorTokenUserUseCase {

    private final ISsoRepository ssoRepository;
    private final ITokenOrchestratorRedisClientRepository tokenBusinessCapabilityRedisClientRepository;
    private final IGetOrchestratorAuthUseCase getOrchestratorAuthUseCase;
    private final IGetOrchestratorLoginUseCase getOrchestratorLoginUseCase;
    private final IGetCalculateEpoch getCalculateEpoch;

    @Value("${sso.authorization.apptoken}")
    private String appToken;

    @Value("${sso.authorization.scopes}")
    private String scopes;

    @Value("${token-to-tokenuser.issuer}")
    private String issuer;

    public GetOrchestratorTokenUserUseCase(ISsoRepository ssoRepository,
                                           ITokenOrchestratorRedisClientRepository
                                                   tokenBusinessCapabilityRedisClientRepository,
                                           IGetOrchestratorAuthUseCase getOrchestratorAuthUseCase,
                                           IGetOrchestratorLoginUseCase getOrchestratorLoginUseCase,
                                           IGetCalculateEpoch getCalculateEpoch) {
        this.ssoRepository = ssoRepository;
        this.tokenBusinessCapabilityRedisClientRepository = tokenBusinessCapabilityRedisClientRepository;
        this.getOrchestratorAuthUseCase = getOrchestratorAuthUseCase;
        this.getOrchestratorLoginUseCase = getOrchestratorLoginUseCase;
        this.getCalculateEpoch = getCalculateEpoch;
    }

    @Override
    public TokenModel invoke() {

        validateExpiration();

        Optional<TokenModel> tableModelToken = tokenBusinessCapabilityRedisClientRepository
                .getToken(STR_POSV2_REFRESH_TOKEN);

        TokenModel refreshToken = new TokenModel();

        if (tableModelToken.isEmpty()) {
            TokenModel tokenAuthorization = getOrchestratorAuthUseCase.invoke(
                    STR_LOGIN_SCOPE, appToken, STR_POSV2_ACCESS_TOKEN_LOGIN
            );

            refreshToken = getOrchestratorLoginUseCase.invoke(
                    tokenAuthorization.getAccessToken(),
                    STR_POSV2_REFRESH_TOKEN
            );
        }

        Optional<TokenModel> tokenModelTokenUser = tokenBusinessCapabilityRedisClientRepository
                .getToken(STR_POSV2_ACCESS_TOKEN);

        if (!tokenModelTokenUser.isEmpty()){
            TokenModel tokenHeader = tokenModelTokenUser.get();
            tokenHeader.setAccessToken(tokenHeader.getAccessToken());

            return tokenHeader;
        }

        log.info("Starting process to save token in redis for token access");

        List<String> itemsScopes = Arrays.asList(scopes.split(CHAR_COMMA));

        TokenModel tokenModelPermissions = getTokenAndSaveInRedis(itemsScopes,
                refreshToken.getAccessToken(),
                STR_POSV2_ACCESS_TOKEN);

        log.info("Token get successfully");

        return tokenModelPermissions;

    }

    public TokenModel getTokenAndSaveInRedis(List<String> scope, String authorizationToken, String getterKey) {

        CredentialsWithScopesPermissionDTO credentialsWithScopesPermissionDTO = CredentialsWithScopesPermissionDTO
                .builder()
                .authorizationToken(authorizationToken)
                .issuer(issuer)
                .scope(scope)
                .build();

        ResponseSSOAuthDTO responseSSOAuthDTO = ssoRepository.authPermissions(credentialsWithScopesPermissionDTO);

        TokenModel tokenModel = responseSSOAuthDTO.toDomainWithExpirationMinute(
                getCalculateEpoch.minutesExpiration(responseSSOAuthDTO.getResult().getAuthExpiration())
        );

        tokenBusinessCapabilityRedisClientRepository.setToken(tokenModel, getterKey);

        return tokenModel;

    }

    private void validateExpiration(){
        Optional<TokenModel> tokenModelAuthLogin = tokenBusinessCapabilityRedisClientRepository
                .getToken(STR_POSV2_ACCESS_TOKEN_LOGIN);

        Optional<TokenModel> tokenModelLogin = tokenBusinessCapabilityRedisClientRepository
                .getToken(STR_POSV2_REFRESH_TOKEN);

        Optional<TokenModel> tokenModelPermissions = tokenBusinessCapabilityRedisClientRepository
                .getToken(STR_POSV2_ACCESS_TOKEN);

        if (tokenModelPermissions.isPresent() && tokenModelLogin.isPresent() && tokenModelAuthLogin.isPresent()) {
            return;
        }
        deleteAllOrchestratorRedis();
    }

    private void deleteAllOrchestratorRedis(){
        tokenBusinessCapabilityRedisClientRepository.deleteAllKeyRedis();
    }

}
