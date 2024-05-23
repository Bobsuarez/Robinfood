package com.robinfood.localorderbc.mappers.security;

import com.robinfood.localorderbc.dtos.security.ResponseSSOLoginScopeDTO;
import com.robinfood.localorderbc.entities.security.ResponseSSOLoginScopeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISsoMapper {

    ResponseSSOLoginScopeDTO responseSSOLoginScopeEntityToResponseSSOLoginScopeDTO(
            ResponseSSOLoginScopeEntity responseSSOLoginScopeEntity);

    ResponseSSOLoginScopeEntity responseSSOLoginScopeDTOToResponseSSOLoginScopeEntity(
            ResponseSSOLoginScopeDTO responseSSOLoginScopeDTO);
}
