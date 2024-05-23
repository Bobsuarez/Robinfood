package com.robinfood.localserver.commons.mappers.token;

import com.robinfood.localserver.commons.dtos.token.TokenModelDTO;
import com.robinfood.localserver.commons.entities.token.TokenModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITokenModelMapper {

    TokenModelDTO tokenModelToTokenModelDTO(TokenModel model);
}
