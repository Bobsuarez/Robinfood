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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * Implementation of ICreateOrdersElectronicBillingRepository
 */
@Repository
@Slf4j
public class SsoRepository implements ISsoRepository {

    private final ISsoMapper ssoMapper;
    private final ISsoAuthenticationMapper ssoAuthenticationMapper;
    private final ICredentialsMapper credentialsMapper;
    private final ICredentialsAuthenticationMapper credentialsAuthenticationMapper;
    private final ICredentialsWithScopesPermissionMapper credentialsWithScopesPermissionMapper;
    private final ISsoDataSource ssoDataSource;

    public SsoRepository(ISsoMapper ssoMapper, ISsoAuthenticationMapper ssoAuthenticationMapper,
                         ICredentialsMapper credentialsMapper,
                         ICredentialsAuthenticationMapper credentialsAuthenticationMapper,
                         ICredentialsWithScopesPermissionMapper credentialsWithScopesPermissionMapper,
                         ISsoDataSource ssoDataSource) {
        this.ssoMapper = ssoMapper;
        this.ssoAuthenticationMapper = ssoAuthenticationMapper;
        this.credentialsMapper = credentialsMapper;
        this.credentialsAuthenticationMapper = credentialsAuthenticationMapper;
        this.credentialsWithScopesPermissionMapper = credentialsWithScopesPermissionMapper;
        this.ssoDataSource = ssoDataSource;
    }

    @Override
    public ResponseSSOLoginScopeDTO login(
            String accessToken,
            CredentialsDTO credentialsDTO) {

        log.info("Execute create Sso repository {}", credentialsDTO);

        CredentialsEntity credentialsEntity = credentialsMapper
                .credentialsDTOToCredentialsEntity(
                        credentialsDTO
                );

        ResponseSSOLoginScopeEntity response = ssoDataSource.login(accessToken, credentialsEntity);

        return ssoMapper
                .responseSSOLoginScopeEntityToResponseSSOLoginScopeDTO(response);
    }

    @Override
    public ResponseSSOAuthDTO auth(CredentialsAuthenticationDTO credentialsAuthenticationDTO) {

        log.info("Execute create Sso authentication {}", credentialsAuthenticationDTO);

        CredentialsAuthenticationEntity credentialsAuthenticationEntity = credentialsAuthenticationMapper
                .credentialsAuthenticationDTOToCredentialsAuthenticationEntity(
                        credentialsAuthenticationDTO
                );

        ResponseSSOAuthEntity responseSSOAuthEntity = ssoDataSource.auth(
                credentialsAuthenticationEntity
        );

        return ssoAuthenticationMapper
                .responseSSOAuthenticationEntityToResponseSSOAuthenticationDTO(
                        responseSSOAuthEntity

                );
    }

    @Override
    public ResponseSSOAuthDTO authPermissions(CredentialsWithScopesPermissionDTO credentialsWithScopesPermissionDTO) {

        log.info("Execute create Sso authentication Permissions {}", credentialsWithScopesPermissionDTO);

        CredentialsWithScopesPermissionEntity credentialsWithScopesPermissionEntity
                = credentialsWithScopesPermissionMapper
                .credentialsWithScopesPermissionDTOToCredentialsWithScopesPermissionEntity(
                        credentialsWithScopesPermissionDTO
                );

        ResponseSSOAuthEntity responseSSOAuthEntity = ssoDataSource.authPermissions(
                credentialsWithScopesPermissionEntity
        );

        return ssoAuthenticationMapper
                .responseSSOAuthenticationEntityToResponseSSOAuthenticationDTO(
                        responseSSOAuthEntity

                );
    }
}
