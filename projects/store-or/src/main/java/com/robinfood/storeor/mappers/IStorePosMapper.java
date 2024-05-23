package com.robinfood.storeor.mappers;

import com.robinfood.storeor.dtos.configurationposbystore.StorePosDTO;
import com.robinfood.storeor.entities.configurationposbystore.StorePosEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IStorePosMapper {
    List<StorePosDTO> storePosEntitiesToStorePosDTOs(List<StorePosEntity> storePosEntities);
}
