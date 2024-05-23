package com.robinfood.storeor.mappers;

import com.robinfood.storeor.dtos.listposresponse.PosListResponseDTO;
import com.robinfood.storeor.entities.listposresponse.PosListResponseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPosResponseMapper {
    PosListResponseDTO posResponseEntityToPosResponseDTO(PosListResponseEntity posResponseEntity);
}
