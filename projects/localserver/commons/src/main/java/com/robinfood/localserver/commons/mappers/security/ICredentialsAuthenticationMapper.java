package com.robinfood.localserver.commons.mappers.security;

import com.robinfood.localserver.commons.dtos.security.CredentialsAuthenticationDTO;
import com.robinfood.localserver.commons.entities.security.CredentialsAuthenticationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICredentialsAuthenticationMapper {

    CredentialsAuthenticationDTO credentialsAuthenticationEntityToCredentialsAuthenticationDTO(
            CredentialsAuthenticationEntity credentialsEntity
    );

    CredentialsAuthenticationEntity credentialsAuthenticationDTOToCredentialsAuthenticationEntity(
            CredentialsAuthenticationDTO credentialsAuthenticationDTO
    );
}
