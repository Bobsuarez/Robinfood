package com.robinfood.localorderbc.usecases.gettokenuser;

import com.robinfood.localorderbc.dtos.security.CredentialsAuthenticationDTO;
import com.robinfood.localorderbc.dtos.security.ResponseSSOAuthDTO;
import com.robinfood.localorderbc.dtos.security.ResponseSSOLoginScopeDTO;
import com.robinfood.localorderbc.entities.token.TokenModel;
import com.robinfood.localorderbc.mocks.token.ResponseSSOAuthDTOMocks;
import com.robinfood.localorderbc.mocks.token.ResponseSSOLoginScopeDTOMocks;
import com.robinfood.localorderbc.repositories.redis.ITokenOrchestratorRedisClientRepository;
import com.robinfood.localorderbc.repositories.redis.ITokenOrchestratorRedisRepository;
import com.robinfood.localorderbc.repositories.sso.ISsoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static com.robinfood.localorderbc.configs.constants.APIConstants.STR_LOGIN_SCOPE;
import static com.robinfood.localorderbc.configs.constants.APIConstants.STR_POSV2_ACCESS_TOKEN_LOGIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class GetOrchestratorAuthUseCaseTest {

    @Mock
    ITokenOrchestratorRedisClientRepository tokenBusinessCapabilityRedisClientRepository;

    @Mock
    private ITokenOrchestratorRedisRepository tokenBusinessCapabilityRedisRepository;

    @Mock
    private IGetCalculateEpoch getCalculateEpoch;

    @InjectMocks
    GetOrchestratorAuthUseCase getOrchestratorAuthUseCase;

    @Mock
    private ISsoRepository ssoRepository;

    private final String BEARER = "Bearer ";

    private String appToken = "appTokenTest";

    private String TOKEN_ACCESS_AUTH = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJiYWNrb2ZmaWNlLnYxIiwiYXVkIjoibG9naW4iLCJtb2QiOltdLCJwZXIiOltdLCJqdGkiOiJmMzhmOWEwMi04NTZlLTQxZWQtODBjMC02YzkzNmQ0M2UwMWQiLCJleHAiOjE2NTIzNDcyNzAsImlhdCI6MTY1MjMwNDA3MCwibmJmIjowLCJjb21wYW55X2lkIjoxfQ.qPdovzTnlTabY8Ddqkr28R9RYuJxvfRduuAsaCCP50ugGggxhimsJQAvNK53saynGaKbMsXqTeFGG5sPvTo8yQ";
    private String TOKEN_EXCEPTION =  "TOKEN_EXCEPTION";

    private ResponseSSOAuthDTO responseSSOAuthDTO =
            new ResponseSSOAuthDTOMocks().responseSSOAuthDTOSuccess;

    private ResponseSSOLoginScopeDTO responseSSOLoginScopeDTOSuccess =
            new ResponseSSOLoginScopeDTOMocks().responseSSOLoginScopeDTOSuccess;

    @Test
    void test_When_getToken_authorization_Success() {

        ReflectionTestUtils.setField(getOrchestratorAuthUseCase, "issuer", "test.v1");

        CredentialsAuthenticationDTO credentialsAuthenticationDTO = CredentialsAuthenticationDTO
                .builder()
                .authorizationToken("appTokenTest")
                .issuer("test.v1")
                .scope("login")
                .build();

        Mockito.when(ssoRepository.auth(credentialsAuthenticationDTO))
                .thenReturn(responseSSOAuthDTO);

       TokenModel tableModel = getOrchestratorAuthUseCase.invoke(STR_LOGIN_SCOPE, appToken, STR_POSV2_ACCESS_TOKEN_LOGIN);

        assertEquals(BEARER.concat(responseSSOAuthDTO.getResult().getAccessToken()), tableModel.getAccessToken());
    }

    @Test
    void test_When_getToken_authorization_Exception() {

        ReflectionTestUtils.setField(getOrchestratorAuthUseCase, "issuer", "backoffice.v1");

        CredentialsAuthenticationDTO credentialsAuthenticationDTO = CredentialsAuthenticationDTO
                .builder()
                .authorizationToken("appTokenTest")
                .issuer("backoffice.v1")
                .scope("login")
                .build();

        responseSSOAuthDTO.getResult().setAccessToken(TOKEN_EXCEPTION);
        Mockito.when(ssoRepository.auth(credentialsAuthenticationDTO))
                .thenReturn(responseSSOAuthDTO);

        TokenModel tableModel = getOrchestratorAuthUseCase.invoke(STR_LOGIN_SCOPE, appToken, STR_POSV2_ACCESS_TOKEN_LOGIN);

        assertNull(tableModel);
    }

}
