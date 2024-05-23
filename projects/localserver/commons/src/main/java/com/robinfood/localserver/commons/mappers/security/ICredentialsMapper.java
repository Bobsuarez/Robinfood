package com.robinfood.localserver.commons.mappers.security;

import com.robinfood.localserver.commons.dtos.security.CredentialsDTO;
import com.robinfood.localserver.commons.entities.security.CredentialsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICredentialsMapper {

    CredentialsDTO credentialsEntityToCredentialsDTO(CredentialsEntity credentialsEntity);

    CredentialsEntity credentialsDTOToCredentialsEntity(CredentialsDTO credentialsDTO);
}
