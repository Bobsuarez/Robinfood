package com.robinfood.localorderbc.mappers.security;

import com.robinfood.localorderbc.dtos.security.CredentialsDTO;
import com.robinfood.localorderbc.entities.security.CredentialsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICredentialsMapper {

    CredentialsDTO credentialsEntityToCredentialsDTO(CredentialsEntity credentialsEntity);

    CredentialsEntity credentialsDTOToCredentialsEntity(CredentialsDTO credentialsDTO);
}
