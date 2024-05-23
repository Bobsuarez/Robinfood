package com.robinfood.localserver.commons.mappers.security;

import com.robinfood.localserver.commons.dtos.security.CredentialsWithScopesPermissionDTO;
import com.robinfood.localserver.commons.entities.security.CredentialsWithScopesPermissionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICredentialsWithScopesPermissionMapper {

    CredentialsWithScopesPermissionDTO credentialsWithScopesPermissionEntityToCredentialsWithScopesPermissionDTO(
            CredentialsWithScopesPermissionEntity credentialsWithScopesPermissionEntity
    );

    CredentialsWithScopesPermissionEntity credentialsWithScopesPermissionDTOToCredentialsWithScopesPermissionEntity(
            CredentialsWithScopesPermissionDTO credentialsWithScopesPermissionDTO
    );

}
