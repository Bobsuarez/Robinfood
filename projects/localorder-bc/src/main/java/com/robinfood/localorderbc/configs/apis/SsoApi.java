package com.robinfood.localorderbc.configs.apis;

import com.robinfood.localorderbc.entities.security.CredentialsAuthenticationEntity;
import com.robinfood.localorderbc.entities.security.CredentialsEntity;
import com.robinfood.localorderbc.entities.security.CredentialsWithScopesPermissionEntity;
import com.robinfood.localorderbc.entities.security.ResponseSSOAuthEntity;
import com.robinfood.localorderbc.entities.security.ResponseSSOLoginScopeEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import static com.robinfood.localorderbc.configs.constants.APIConstants.AUTHORIZATION_HEADER_KEY;

@FeignClient(value = "feignSso", url = "${feign.client.url.sso}")
public interface SsoApi {

    /**
     * Connects to an endpoint sso fot login
     * @param credentialsEntity Request body credentials for do login
     * @return tokens of access
     */
    @PostMapping("v1/login")
    ResponseSSOLoginScopeEntity login(
            @RequestHeader(AUTHORIZATION_HEADER_KEY) String token,
            @RequestBody CredentialsEntity credentialsEntity
    );

    /**
     * Connects to an endpoint sso fot authentication
     * @param credentialsAuthenticationEntity Request body credentials for do auth
     * @return tokens of access for login y scope
     */
    @PostMapping("v1/auth")
    ResponseSSOAuthEntity auth(
            @RequestBody CredentialsAuthenticationEntity credentialsAuthenticationEntity
    );

    /**
     * Connects to an endpoint sso. To obtain permissions to the modules
     * @param credentialsWithScopesPermissionEntity Request body credentials for do auth
     * @return tokens of access fot applications
     */
    @PostMapping("v1/auth")
    ResponseSSOAuthEntity authPermissions(
            @RequestBody CredentialsWithScopesPermissionEntity credentialsWithScopesPermissionEntity
    );

}
