package com.robinfood.localorderbc.mappers.security;

import com.robinfood.localorderbc.dtos.security.CredentialsAuthenticationDTO;
import com.robinfood.localorderbc.entities.security.CredentialsAuthenticationEntity;
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
