package com.robinfood.localorderbc.repositories.sso;

import com.robinfood.localorderbc.dtos.security.CredentialsAuthenticationDTO;
import com.robinfood.localorderbc.dtos.security.CredentialsDTO;
import com.robinfood.localorderbc.dtos.security.CredentialsWithScopesPermissionDTO;
import com.robinfood.localorderbc.dtos.security.ResponseSSOAuthDTO;
import com.robinfood.localorderbc.dtos.security.ResponseSSOLoginScopeDTO;


/**
 * Repository that handles the creation of the order SSO response.
 */

public interface ISsoRepository {

    /**
     * Returns the result token login
     * @param  accessToken Token of access
     * @param credentialsDTO  Credentials for login
     * @return token of access for get token access user
     */
    ResponseSSOLoginScopeDTO login(
            String accessToken,
            CredentialsDTO credentialsDTO

    );

    /**
     * Returns the result of token authorization for login
     * @param credentialsAuthenticationDTO the info to credentials
     * @return Response Sso Authorization
     */
    ResponseSSOAuthDTO auth(
            CredentialsAuthenticationDTO credentialsAuthenticationDTO
    );

    /**
     * Returns response sso authorization permissions
     * @param credentialsWithScopesPermissionDTO the info to permissions response
     * @return All Permissions
     */
    ResponseSSOAuthDTO authPermissions(
            CredentialsWithScopesPermissionDTO credentialsWithScopesPermissionDTO
    );
}
