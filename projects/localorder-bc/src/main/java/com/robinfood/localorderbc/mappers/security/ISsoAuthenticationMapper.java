package com.robinfood.localorderbc.mappers.security;

import com.robinfood.localorderbc.dtos.security.ResponseSSOAuthDTO;
import com.robinfood.localorderbc.entities.security.ResponseSSOAuthEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISsoAuthenticationMapper {

    ResponseSSOAuthDTO responseSSOAuthenticationEntityToResponseSSOAuthenticationDTO(
            ResponseSSOAuthEntity responseSSOAuthEntity);

    ResponseSSOAuthEntity responseSSOAuthenticationDTOToResponseSSOAuthenticationEntity(
            ResponseSSOAuthDTO responseSSOAuthDTO);
}
