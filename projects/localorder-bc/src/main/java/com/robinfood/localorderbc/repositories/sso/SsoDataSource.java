package com.robinfood.localorderbc.repositories.sso;

import com.robinfood.localorderbc.configs.apis.SsoApi;
import com.robinfood.localorderbc.entities.security.CredentialsAuthenticationEntity;
import com.robinfood.localorderbc.entities.security.CredentialsEntity;
import com.robinfood.localorderbc.entities.security.CredentialsWithScopesPermissionEntity;
import com.robinfood.localorderbc.entities.security.ResponseSSOAuthEntity;
import com.robinfood.localorderbc.entities.security.ResponseSSOLoginScopeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * Implementation of ISsoDataSource
 */
@Slf4j
@Repository
public class SsoDataSource implements ISsoDataSource {

    private final SsoApi ssoApi;

    public SsoDataSource(SsoApi ssoApi) {
        this.ssoApi = ssoApi;
    }

    @Override
    public ResponseSSOLoginScopeEntity login(
            String accessToken,
            CredentialsEntity credentialsRequestEntity
    ) {

        ResponseSSOLoginScopeEntity responseEntityResult = null;
        try {
            log.info("Execute create SSO remote Data Source {}", credentialsRequestEntity);

            responseEntityResult = ssoApi.login(accessToken, credentialsRequestEntity);

            log.info("The order SSO get successfully {}", responseEntityResult);
        } catch (Exception e) {
            log.error("Error at get SSO remote Data Source", e);
        }

        return responseEntityResult;
    }

    @Override
    public ResponseSSOAuthEntity auth(CredentialsAuthenticationEntity credentialsAuthenticationEntity) {

        log.info("Execute create SSO authentication remote Data Source {}", credentialsAuthenticationEntity);

        ResponseSSOAuthEntity responseSSOAuthEntity = ssoApi.auth(credentialsAuthenticationEntity);

        log.info("The order SSO Authentication get successfully {}", responseSSOAuthEntity);

        return responseSSOAuthEntity;
    }

    @Override
    public ResponseSSOAuthEntity authPermissions(
            CredentialsWithScopesPermissionEntity credentialsWithScopesPermissionEntity) {

        log.info("Execute create SSO authentication remote Data Source {}", credentialsWithScopesPermissionEntity);

        ResponseSSOAuthEntity responseSSOAuthEntity = ssoApi.authPermissions(credentialsWithScopesPermissionEntity);

        log.info("The order SSO Authentication get successfully {}", responseSSOAuthEntity);

        return responseSSOAuthEntity;
    }
}
