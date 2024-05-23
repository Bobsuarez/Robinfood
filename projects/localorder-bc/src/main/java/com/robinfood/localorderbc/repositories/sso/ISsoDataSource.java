package com.robinfood.localorderbc.repositories.sso;

import com.robinfood.localorderbc.entities.security.CredentialsAuthenticationEntity;
import com.robinfood.localorderbc.entities.security.CredentialsEntity;
import com.robinfood.localorderbc.entities.security.CredentialsWithScopesPermissionEntity;
import com.robinfood.localorderbc.entities.security.ResponseSSOAuthEntity;
import com.robinfood.localorderbc.entities.security.ResponseSSOLoginScopeEntity;

/**
 * Remote configuration data source that connects to external APIs to get token response
 */
public interface ISsoDataSource {

    /**
     * Returns the result token login
     * @param  accessToken Token of access
     * @param credentialsEntity  Credentials for login
     * @return token of access for get token access user
     */
    ResponseSSOLoginScopeEntity login(
            String accessToken,
            CredentialsEntity credentialsEntity
    );

    /**
     * Returns the result of token authorization for login
     * @param credentialsAuthenticationEntity the info to credentials
     * @return Response Sso Authorization
     */
    ResponseSSOAuthEntity auth(
            CredentialsAuthenticationEntity credentialsAuthenticationEntity
    );

    /**
     * Returns response sso authorization permissions
     * @param credentialsWithScopesPermissionEntity the info to permissions response
     * @return All Permissions
     */
    ResponseSSOAuthEntity authPermissions(
            CredentialsWithScopesPermissionEntity credentialsWithScopesPermissionEntity
    );
}
