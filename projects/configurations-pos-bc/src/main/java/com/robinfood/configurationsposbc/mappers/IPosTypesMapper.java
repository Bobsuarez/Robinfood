package com.robinfood.configurationsposbc.mappers;

import com.robinfood.configurationsposbc.dtos.pos.PosTypesDTO;
import com.robinfood.configurationsposbc.entities.PosTypesEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPosTypesMapper {

    PosTypesDTO posTypesEntityToPosTypesDTO(PosTypesEntity posTypesEntity);
}
