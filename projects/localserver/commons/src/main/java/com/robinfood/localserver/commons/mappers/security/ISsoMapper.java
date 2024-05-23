package com.robinfood.localserver.commons.mappers.security;

import com.robinfood.localserver.commons.dtos.security.ResponseSSOLoginScopeDTO;
import com.robinfood.localserver.commons.entities.security.ResponseSSOLoginScopeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISsoMapper {

    ResponseSSOLoginScopeDTO responseSSOLoginScopeEntityToResponseSSOLoginScopeDTO(
            ResponseSSOLoginScopeEntity responseSSOLoginScopeEntity);

    ResponseSSOLoginScopeEntity responseSSOLoginScopeDTOToResponseSSOLoginScopeEntity(
            ResponseSSOLoginScopeDTO responseSSOLoginScopeDTO);
}
