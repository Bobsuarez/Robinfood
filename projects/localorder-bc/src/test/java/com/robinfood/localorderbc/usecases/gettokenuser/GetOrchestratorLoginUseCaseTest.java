package com.robinfood.localorderbc.usecases.gettokenuser;


import com.robinfood.localorderbc.dtos.security.CredentialsDTO;
import com.robinfood.localorderbc.dtos.security.ResponseSSOAuthDTO;
import com.robinfood.localorderbc.dtos.security.ResponseSSOLoginScopeDTO;
import com.robinfood.localorderbc.entities.token.TokenModel;
import com.robinfood.localorderbc.mocks.token.ResponseSSOAuthDTOMocks;
import com.robinfood.localorderbc.mocks.token.ResponseSSOLoginScopeDTOMocks;
import com.robinfood.localorderbc.repositories.redis.ITokenOrchestratorRedisClientRepository;
import com.robinfood.localorderbc.repositories.sso.ISsoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.robinfood.localorderbc.configs.constants.APIConstants.STR_POSV2_REFRESH_TOKEN;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class GetOrchestratorLoginUseCaseTest {

    @Mock
    ITokenOrchestratorRedisClientRepository tokenBusinessCapabilityRedisClientRepository;

    @InjectMocks
    GetOrchestratorLoginUseCase getOrchestratorLoginUseCase;

    @Mock
    private IGetCalculateEpoch getCalculateEpoch;

    @Mock
    private ISsoRepository ssoRepository;

    private final String BEARER = "Bearer ";

    private final String TOKEN_ACCESS_LOGIN = "TOKEN_ACCESS_LOGIN";

    private String email;

    private String password;

    private ResponseSSOAuthDTO responseSSOAuthDTO =
            new ResponseSSOAuthDTOMocks().responseSSOAuthDTOSuccess;

    private ResponseSSOLoginScopeDTO responseSSOLoginScopeDTOSuccess =
            new ResponseSSOLoginScopeDTOMocks().responseSSOLoginScopeDTOSuccess;

    @Test
    void test_When_getToken_login_Success() {

        final CredentialsDTO credentialsDTO = CredentialsDTO.builder()
                .email(email)
                .password(password)
                .build();

        Mockito.when(ssoRepository.login(TOKEN_ACCESS_LOGIN, credentialsDTO))
                .thenReturn(responseSSOLoginScopeDTOSuccess);

       TokenModel tableModel = getOrchestratorLoginUseCase.invoke(TOKEN_ACCESS_LOGIN, STR_POSV2_REFRESH_TOKEN);

        assertEquals(responseSSOLoginScopeDTOSuccess.getResult().getToken().getToken().getRefreshToken()
                , tableModel.getAccessToken());
    }

}
