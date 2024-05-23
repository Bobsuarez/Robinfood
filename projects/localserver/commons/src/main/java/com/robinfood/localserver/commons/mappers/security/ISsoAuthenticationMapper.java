package com.robinfood.localserver.commons.mappers.security;

import com.robinfood.localserver.commons.dtos.security.ResponseSSOAuthDTO;
import com.robinfood.localserver.commons.entities.security.ResponseSSOAuthEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISsoAuthenticationMapper {

    ResponseSSOAuthDTO responseSSOAuthenticationEntityToResponseSSOAuthenticationDTO(
            ResponseSSOAuthEntity responseSSOAuthEntity);

    ResponseSSOAuthEntity responseSSOAuthenticationDTOToResponseSSOAuthenticationEntity(
            ResponseSSOAuthDTO responseSSOAuthDTO);
}
