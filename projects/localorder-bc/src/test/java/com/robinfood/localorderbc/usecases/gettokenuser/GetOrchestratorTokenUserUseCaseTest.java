package com.robinfood.localorderbc.usecases.gettokenuser;


import com.robinfood.localorderbc.dtos.security.CredentialsWithScopesPermissionDTO;
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
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.robinfood.localorderbc.configs.constants.APIConstants.STR_POSV2_ACCESS_TOKEN;
import static com.robinfood.localorderbc.configs.constants.APIConstants.STR_POSV2_ACCESS_TOKEN_LOGIN;
import static com.robinfood.localorderbc.configs.constants.APIConstants.STR_POSV2_REFRESH_TOKEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class GetOrchestratorTokenUserUseCaseTest {

    @InjectMocks
    GetOrchestratorTokenUserUseCase getOrchestratorTokenUserUseCase;

    @Mock
    ISsoRepository ssoRepository;

    @Mock
    ITokenOrchestratorRedisClientRepository tokenBusinessCapabilityRedisClientRepository;

    @Mock
    private IGetOrchestratorAuthUseCase getOrchestratorAuthUseCase;

    @Mock
    private IGetOrchestratorLoginUseCase getOrchestratorLoginUseCase;

    @Mock
    private IGetCalculateEpoch getCalculateEpoch;

    private String appToken;

    private String scopes;

    private final String TOKEN_ACCESS_LOGIN = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJiYWNrb2ZmaWNlLnYxIiwiYXVkIjoibG9naW4iLCJtb2QiOltdLCJwZXIiOltdLCJqdGkiOiJmMzhmOWEwMi04NTZlLTQxZWQtODBjMC02YzkzNmQ0M2UwMWQiLCJleHAiOjE2NTIzNDcyNzAsImlhdCI6MTY1MjMwNDA3MCwibmJmIjowLCJjb21wYW55X2lkIjoxfQ.qPdovzTnlTabY8Ddqkr28R9RYuJxvfRduuAsaCCP50ugGggxhimsJQAvNK53saynGaKbMsXqTeFGG5sPvTo8yQ";
    private final String TOKEN_ACCESS_AUTH = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJiYWNrb2ZmaWNlLnYxIiwiYXVkIjoibG9naW4iLCJtb2QiOltdLCJwZXIiOltdLCJqdGkiOiJmMzhmOWEwMi04NTZlLTQxZWQtODBjMC02YzkzNmQ0M2UwMWQiLCJleHAiOjE2NTIzNDcyNzAsImlhdCI6MTY1MjMwNDA3MCwibmJmIjowLCJjb21wYW55X2lkIjoxfQ.qPdovzTnlTabY8Ddqkr28R9RYuJxvfRduuAsaCCP50ugGggxhimsJQAvNK53saynGaKbMsXqTeFGG5sPvTo8yQ";
    private final String SCOPE_LOGIN = "login";
    private final String BEARER = "Bearer ";

    private final ResponseSSOAuthDTO responseSSOAuthDTO =
            new ResponseSSOAuthDTOMocks().responseSSOAuthDTOSuccess;

    private final ResponseSSOLoginScopeDTO responseSSOLoginScopeDTOSuccess =
            new ResponseSSOLoginScopeDTOMocks().responseSSOLoginScopeDTOSuccess;


    @Test
    void test_When_getToken_Success() {

        List<String> scopesList = new ArrayList<>();
        scopesList.add("testscope");

        ReflectionTestUtils.setField(getOrchestratorTokenUserUseCase, "issuer", "backoffice.v1");

        CredentialsWithScopesPermissionDTO credentialsWithScopesPermissionDTO = CredentialsWithScopesPermissionDTO
                .builder()
                .authorizationToken(TOKEN_ACCESS_LOGIN)
                .issuer("backoffice.v1")
                .scope(scopesList)
                .build();

        Optional<TokenModel> tokenModel = Optional.empty();

        Mockito.when(tokenBusinessCapabilityRedisClientRepository.getToken(STR_POSV2_ACCESS_TOKEN_LOGIN))
                .thenReturn(tokenModel);

        Mockito.when(tokenBusinessCapabilityRedisClientRepository.getToken(STR_POSV2_REFRESH_TOKEN))
                .thenReturn(tokenModel);

        Mockito.when(tokenBusinessCapabilityRedisClientRepository.getToken(STR_POSV2_ACCESS_TOKEN))
                .thenReturn(tokenModel);

        Mockito.when(ssoRepository.authPermissions(credentialsWithScopesPermissionDTO))
                .thenReturn(responseSSOAuthDTO);

        TokenModel tokenModel1Auth = TokenModel.builder()
                .accessToken(TOKEN_ACCESS_AUTH)
                .expiresIn(12345678L)
                .build();

        Mockito.when(getOrchestratorAuthUseCase.invoke(SCOPE_LOGIN, appToken, STR_POSV2_ACCESS_TOKEN_LOGIN))
                .thenReturn(tokenModel1Auth);

        TokenModel tokenModel1Login = TokenModel.builder()
                .accessToken(TOKEN_ACCESS_LOGIN)
                .expiresIn(12345678L)
                .build();

        Mockito.when(getOrchestratorLoginUseCase.invoke(tokenModel1Auth.getAccessToken(), STR_POSV2_REFRESH_TOKEN))
                .thenReturn(tokenModel1Login);


        ReflectionTestUtils.setField(getOrchestratorTokenUserUseCase, "scopes", "testscope");

        TokenModel tableModel = getOrchestratorTokenUserUseCase.invoke();

        assertEquals(BEARER.concat(TOKEN_ACCESS_AUTH), tableModel.getAccessToken());

    }

    @Test
    void test_When_getToken_get_Success() {

        List<String> scopesList = new ArrayList<>();
        scopesList.add("testscope");

        Optional<TokenModel> tokenModel = Optional.of(new TokenModel());

        Mockito.when(tokenBusinessCapabilityRedisClientRepository.getToken(STR_POSV2_ACCESS_TOKEN_LOGIN))
                .thenReturn(tokenModel);

        Mockito.when(tokenBusinessCapabilityRedisClientRepository.getToken(STR_POSV2_REFRESH_TOKEN))
                .thenReturn(tokenModel);

        TokenModel token = TokenModel.builder()
                .accessToken(TOKEN_ACCESS_AUTH)
                .expiresIn(12345678L)
                .build();

        Optional<TokenModel> optionalToken = Optional.of(token);

        Mockito.when(tokenBusinessCapabilityRedisClientRepository.getToken(STR_POSV2_ACCESS_TOKEN))
                .thenReturn(optionalToken);

        ReflectionTestUtils.setField(getOrchestratorTokenUserUseCase, "scopes", "testscope");

        TokenModel tableModel = getOrchestratorTokenUserUseCase.invoke();

        assertEquals(TOKEN_ACCESS_AUTH, tableModel.getAccessToken());

    }

    @Test
    void test_When_getToken_get_Login_Success_empty() {

        List<String> scopesList = new ArrayList<>();
        scopesList.add("testscope");

        Optional<TokenModel> tokenModel_ = Optional.empty();

        Optional<TokenModel> tokenModel = Optional.of(new TokenModel());

        Mockito.when(tokenBusinessCapabilityRedisClientRepository.getToken(STR_POSV2_ACCESS_TOKEN_LOGIN))
                .thenReturn(tokenModel_);

        Mockito.when(tokenBusinessCapabilityRedisClientRepository.getToken(STR_POSV2_REFRESH_TOKEN))
                .thenReturn(tokenModel);

        TokenModel token = TokenModel.builder()
                .accessToken(TOKEN_ACCESS_AUTH)
                .expiresIn(12345678L)
                .build();

        Optional<TokenModel> optionalToken = Optional.of(token);

        Mockito.when(tokenBusinessCapabilityRedisClientRepository.getToken(STR_POSV2_ACCESS_TOKEN))
                .thenReturn(optionalToken);

        ReflectionTestUtils.setField(getOrchestratorTokenUserUseCase, "scopes", "testscope");

        TokenModel tableModel = getOrchestratorTokenUserUseCase.invoke();

        assertEquals(TOKEN_ACCESS_AUTH, tableModel.getAccessToken());

    }

    @Test
    void test_When_getToken_get_Refresh_Success_empty() {

        List<String> scopesList = new ArrayList<>();
        scopesList.add("testscope");

        Optional<TokenModel> tokenModel_ = Optional.empty();

        Optional<TokenModel> tokenModel = Optional.of(new TokenModel());

        Mockito.when(tokenBusinessCapabilityRedisClientRepository.getToken(STR_POSV2_ACCESS_TOKEN_LOGIN))
                .thenReturn(tokenModel);

        Mockito.when(tokenBusinessCapabilityRedisClientRepository.getToken(STR_POSV2_REFRESH_TOKEN))
                .thenReturn(tokenModel_);

        TokenModel tokenModel1Auth = TokenModel.builder()
                .accessToken(TOKEN_ACCESS_AUTH)
                .expiresIn(12345678L)
                .build();

        Optional<TokenModel> optionalToken = Optional.of(new TokenModel());

        Mockito.when(getOrchestratorAuthUseCase.invoke(SCOPE_LOGIN, appToken, STR_POSV2_ACCESS_TOKEN_LOGIN))
                .thenReturn(tokenModel1Auth);

        TokenModel tokenModel1Login = TokenModel.builder()
                .accessToken(TOKEN_ACCESS_LOGIN)
                .expiresIn(12345678L)
                .build();

        Mockito.when(getOrchestratorLoginUseCase.invoke(tokenModel1Auth.getAccessToken(), STR_POSV2_REFRESH_TOKEN))
                .thenReturn(tokenModel1Login);

        Mockito.when(tokenBusinessCapabilityRedisClientRepository.getToken(STR_POSV2_ACCESS_TOKEN))
                .thenReturn(optionalToken);

        ReflectionTestUtils.setField(getOrchestratorTokenUserUseCase, "scopes", "testscope");

        TokenModel tableModel = getOrchestratorTokenUserUseCase.invoke();

        assertNull(tableModel.getAccessToken());

    }
}
