package com.robinfood.localorderbc.mappers.security;

import com.robinfood.localorderbc.dtos.security.CredentialsWithScopesPermissionDTO;
import com.robinfood.localorderbc.entities.security.CredentialsWithScopesPermissionEntity;
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
