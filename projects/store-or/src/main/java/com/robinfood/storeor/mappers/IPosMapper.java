package com.robinfood.storeor.mappers;

import com.robinfood.storeor.dtos.configurationpos.StoreCreatePosDTO;
import com.robinfood.storeor.dtos.response.PosResponseDTO;
import com.robinfood.storeor.entities.PosEntity;
import com.robinfood.storeor.entities.StoreCreatePosEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPosMapper {
    PosResponseDTO posEntityToPosResponseDTO(PosEntity posEntity);

    StoreCreatePosDTO storeCreatePosEntityToStoreCreatePosDTO (StoreCreatePosEntity storeCreatePosEntity);

    StoreCreatePosEntity storeCreatePosDTOToStoreCreatePosEntity (StoreCreatePosDTO storeCreatePosDTO);
}
