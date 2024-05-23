package com.robinfood.localorderbc.repositories.sso;

import com.robinfood.localorderbc.dtos.security.CredentialsAuthenticationDTO;
import com.robinfood.localorderbc.dtos.security.CredentialsDTO;
import com.robinfood.localorderbc.dtos.security.CredentialsWithScopesPermissionDTO;
import com.robinfood.localorderbc.dtos.security.ResponseSSOAuthDTO;
import com.robinfood.localorderbc.dtos.security.ResponseSSOLoginScopeDTO;
import com.robinfood.localorderbc.entities.security.CredentialsAuthenticationEntity;
import com.robinfood.localorderbc.entities.security.CredentialsEntity;
import com.robinfood.localorderbc.entities.security.CredentialsWithScopesPermissionEntity;
import com.robinfood.localorderbc.entities.security.ResponseSSOAuthEntity;
import com.robinfood.localorderbc.entities.security.ResponseSSOLoginScopeEntity;
import com.robinfood.localorderbc.mappers.security.ICredentialsAuthenticationMapper;
import com.robinfood.localorderbc.mappers.security.ICredentialsMapper;
import com.robinfood.localorderbc.mappers.security.ICredentialsWithScopesPermissionMapper;
import com.robinfood.localorderbc.mappers.security.ISsoAuthenticationMapper;
import com.robinfood.localorderbc.mappers.security.ISsoMapper;
import com.robinfood.localorderbc.mocks.token.ResponseSSOAuthDTOMocks;
import com.robinfood.localorderbc.mocks.token.ResponseSSOAuthEntityMocks;
import com.robinfood.localorderbc.mocks.token.ResponseSSOLoginScopeDTOMocks;
import com.robinfood.localorderbc.mocks.token.ResponseSSOLoginScopeEntityMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class SsoRepositoryTest {

    @Mock
    ISsoDataSource ssoDataSource;

    @Mock
    private ICredentialsMapper credentialsMapper;

    @Mock
    private ISsoMapper ssoMapper;

    @Mock
    private ISsoAuthenticationMapper ssoAuthenticationMapper;

    @Mock
    private ICredentialsAuthenticationMapper credentialsAuthenticationMapper;

    @Mock
    private ICredentialsWithScopesPermissionMapper credentialsWithScopesPermissionMapper;

    @InjectMocks
    SsoRepository ssoRepository;

    private final String TOKEN_ACCESS_LOGIN = "TOKEN_ACCESS_LOGIN";
    private final String TOKEN_ACCESS_AUTH = "TOKEN_ACCESS_AUTH";
    private final String TOKEN_AUTHORIZATION = "TOKEN_AUTHORIZATION";
    private final String TOKEN_PERMISSIONS = "TOKEN_PERMISSIONS";
    private final String ISSUER = "usertest.v1";
    private final String SCOPE = "login";

    private final ResponseSSOLoginScopeDTO responseSSOLoginScopeDTOSuccess = new ResponseSSOLoginScopeDTOMocks()
            .responseSSOLoginScopeDTOSuccess;

    private final ResponseSSOLoginScopeEntity responseSSOLoginScopeEntitySuccess =
            new ResponseSSOLoginScopeEntityMocks().responseSSOLoginScopeEntitySuccess;

    private final ResponseSSOAuthEntity responseSSOAuthEntity =
            new ResponseSSOAuthEntityMocks().responseSSOAuthEntitySuccess;

    private final ResponseSSOAuthDTO responseSSOAuthDTO =
            new ResponseSSOAuthDTOMocks().responseSSOAuthDTOSuccess;

    @Test
    void test_When_Sso_Data_Source_Login_Repository_Success() {

        CredentialsDTO credentialsDto = new CredentialsDTO(
                "test@email.com",
                "x1x3"
        );

        CredentialsEntity credentialsEntity = new CredentialsEntity(
                "test@email.com",
                "x1x3"
        );

        Mockito.when(credentialsMapper.credentialsDTOToCredentialsEntity(
                credentialsDto
        )).thenReturn(credentialsEntity);

        Mockito.when(ssoDataSource.login(
                TOKEN_ACCESS_LOGIN,
                credentialsEntity
        )).thenReturn(responseSSOLoginScopeEntitySuccess);

        Mockito.when(ssoMapper.responseSSOLoginScopeEntityToResponseSSOLoginScopeDTO(
                responseSSOLoginScopeEntitySuccess
        )).thenReturn(responseSSOLoginScopeDTOSuccess);

        ResponseSSOLoginScopeDTO responseSSOLoginScopeDTO = ssoRepository.login(
                TOKEN_ACCESS_LOGIN,
                credentialsDto
        );

        assertEquals(responseSSOLoginScopeDTO.getCode(), responseSSOLoginScopeEntitySuccess.getCode());

    }

    @Test
    void test_When_Sso_Data_Source_Auth_Repository_Success() {

        CredentialsAuthenticationDTO credentialsAuthenticationDTO = new CredentialsAuthenticationDTO(
                TOKEN_ACCESS_AUTH,
                ISSUER,
                SCOPE
        );

        CredentialsAuthenticationEntity credentialsAuthenticationEntity = new CredentialsAuthenticationEntity(
                TOKEN_ACCESS_AUTH,
                ISSUER,
                SCOPE
        );

        Mockito.when(credentialsAuthenticationMapper.credentialsAuthenticationDTOToCredentialsAuthenticationEntity(
                credentialsAuthenticationDTO
        )).thenReturn(credentialsAuthenticationEntity);

        Mockito.when(ssoDataSource.auth(
                credentialsAuthenticationEntity
        )).thenReturn(responseSSOAuthEntity);

        Mockito.when(ssoAuthenticationMapper
                .responseSSOAuthenticationEntityToResponseSSOAuthenticationDTO(
                responseSSOAuthEntity
        )).thenReturn(responseSSOAuthDTO);

        ResponseSSOAuthDTO responseSSOAuthSuccessDTO = ssoRepository.auth(
                credentialsAuthenticationDTO
        );

        assertEquals(responseSSOAuthSuccessDTO.getCode(), responseSSOAuthDTO.getCode());

    }

    @Test
    void test_When_Sso_Data_Source_Auth_Permission_Repository_Success() {

        List<String> scopes = new ArrayList<>();
        scopes.add("test1");
        scopes.add("test2");

        CredentialsWithScopesPermissionDTO credentialsAuthenticationDTO = new CredentialsWithScopesPermissionDTO(
                TOKEN_ACCESS_AUTH,
                ISSUER,
                scopes
        );

        CredentialsWithScopesPermissionEntity credentialsAuthenticationEntity = new CredentialsWithScopesPermissionEntity(
                TOKEN_ACCESS_AUTH,
                ISSUER,
                scopes
        );

        Mockito.when(credentialsWithScopesPermissionMapper
                .credentialsWithScopesPermissionDTOToCredentialsWithScopesPermissionEntity(
                credentialsAuthenticationDTO
        )).thenReturn(credentialsAuthenticationEntity);

        Mockito.when(ssoDataSource.authPermissions(
                credentialsAuthenticationEntity
        )).thenReturn(responseSSOAuthEntity);

        Mockito.when(ssoAuthenticationMapper
                .responseSSOAuthenticationEntityToResponseSSOAuthenticationDTO(
                        responseSSOAuthEntity
                )).thenReturn(responseSSOAuthDTO);

        ResponseSSOAuthDTO responseSSOAuthSuccessDTO = ssoRepository.authPermissions(
                credentialsAuthenticationDTO
        );

        assertEquals(responseSSOAuthSuccessDTO.getCode(), responseSSOAuthDTO.getCode());
    }
}
