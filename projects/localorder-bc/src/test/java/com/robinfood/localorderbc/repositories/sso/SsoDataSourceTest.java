package com.robinfood.localorderbc.repositories.sso;

import com.robinfood.localorderbc.configs.apis.SsoApi;
import com.robinfood.localorderbc.entities.security.CredentialsAuthenticationEntity;
import com.robinfood.localorderbc.entities.security.CredentialsEntity;
import com.robinfood.localorderbc.entities.security.CredentialsWithScopesPermissionEntity;
import com.robinfood.localorderbc.entities.security.ResponseDataOkSSOAuthEntity;
import com.robinfood.localorderbc.entities.security.ResponseSSOAuthEntity;
import com.robinfood.localorderbc.entities.security.ResponseSSOLoginScopeEntity;
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
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class SsoDataSourceTest {

    @Mock
    private SsoApi ssoApi;

    @InjectMocks
    SsoDataSource ssoDataSource;

    private final String TOKEN_ACCESS_LOGIN = "TOKEN_ACCESS_LOGIN";
    private final String TOKEN_ACCESS_AUTH = "TOKEN_ACCESS_AUTH";
    private final String TOKEN_AUTHORIZATION = "TOKEN_AUTHORIZATION";
    private final String TOKEN_PERMISSIONS = "TOKEN_PERMISSIONS";
    private final String ISSUER = "usertest.v1";
    private final String SCOPE = "login";

    private final ResponseSSOLoginScopeEntity responseSSOLoginScopeEntitySuccess =
            new ResponseSSOLoginScopeEntityMocks().responseSSOLoginScopeEntitySuccess;

    final ResponseDataOkSSOAuthEntity responseDataOkSSOAuthEntity = ResponseDataOkSSOAuthEntity
            .builder()
            .accessToken(TOKEN_ACCESS_AUTH)
            .authExpiration(123456L)
            .build();

   final ResponseSSOAuthEntity responseSSOAuthEntitySuccess = ResponseSSOAuthEntity
           .builder()
           .code(200)
           .result(responseDataOkSSOAuthEntity)
           .status("OK")
           .build();

    final ResponseSSOAuthEntity responseSSOAuthEntityException = ResponseSSOAuthEntity
            .builder()
            .result(responseDataOkSSOAuthEntity)
            .build();

    @Test
    void test_When_Sso_Data_Source_Login_Success() {

        CredentialsEntity credentialsEntity = new CredentialsEntity(
                "test@email.com",
                "x1x3"
        );

        Mockito.when(ssoApi.login(TOKEN_ACCESS_LOGIN,
                credentialsEntity
        )).thenReturn(responseSSOLoginScopeEntitySuccess);

        ResponseSSOLoginScopeEntity responseSSOLoginScopeEntity = ssoDataSource.login(
                TOKEN_ACCESS_LOGIN,
                credentialsEntity
        );

        assertEquals(responseSSOLoginScopeEntity.getCode(), responseSSOLoginScopeEntitySuccess.getCode());

    }

    @Test
    void test_When_Sso_Data_Source_Login_Exception() {

        CredentialsEntity credentialsEntity = new CredentialsEntity();

        Mockito.when(ssoApi.login(TOKEN_ACCESS_LOGIN,
                credentialsEntity
        )).thenReturn(responseSSOLoginScopeEntitySuccess);

        ResponseSSOLoginScopeEntity responseSSOLoginScopeEntity = ssoDataSource.login(
                null,
                credentialsEntity
        );

        assertNull(responseSSOLoginScopeEntity);

    }

    @Test
    void test_When_Sso_Data_Source_Auth_Success() {

        CredentialsAuthenticationEntity credentialsAuthenticationEntity = CredentialsAuthenticationEntity
                .builder()
                .authorizationToken(TOKEN_AUTHORIZATION)
                .issuer(ISSUER)
                .scope(SCOPE)
                .build();

        Mockito.when(ssoApi.auth(credentialsAuthenticationEntity)).thenReturn(responseSSOAuthEntitySuccess);

        ResponseSSOAuthEntity responseSSOAuthEntity = ssoDataSource.auth(
                credentialsAuthenticationEntity
        );

        assertEquals(responseSSOAuthEntity.getCode(), responseSSOAuthEntitySuccess.getCode());

    }

    @Test
    void test_When_Sso_Data_Source_Auth_Permissions_Success() {

        List<String> scopes = new ArrayList<>();
        scopes.add("test1");
        scopes.add("test2");

        CredentialsWithScopesPermissionEntity credentialsWithScopesPermissionEntity =
                CredentialsWithScopesPermissionEntity
                .builder()
                .authorizationToken(TOKEN_PERMISSIONS)
                .issuer(ISSUER)
                .scope(scopes)
                .build();

        Mockito.when(ssoApi.authPermissions(credentialsWithScopesPermissionEntity))
                .thenReturn(responseSSOAuthEntitySuccess);

        ResponseSSOAuthEntity responseSSOAuthEntity = ssoDataSource.authPermissions(
                credentialsWithScopesPermissionEntity
        );

        assertEquals(responseSSOAuthEntity.getCode(), responseSSOAuthEntitySuccess.getCode());

    }
}
