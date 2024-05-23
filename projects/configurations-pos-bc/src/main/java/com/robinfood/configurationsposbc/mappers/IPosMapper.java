package com.robinfood.configurationsposbc.mappers;

import com.robinfood.configurationsposbc.dtos.pos.PosDTO;
import com.robinfood.configurationsposbc.dtos.pos.PosResponseDTO;
import com.robinfood.configurationsposbc.entities.PosEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IPosMapper {

    PosDTO posEntityToPosDTO(PosEntity posEntity);

    @Mappings({
            @Mapping(target = "posTypes", ignore = true)
    })
    PosResponseDTO posDTOToPosResponseDTO(PosDTO posDTO);

}
