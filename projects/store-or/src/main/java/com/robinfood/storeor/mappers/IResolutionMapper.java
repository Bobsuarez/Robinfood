package com.robinfood.storeor.mappers;

import com.robinfood.storeor.dtos.configurationposbystore.ResolutionDTO;
import com.robinfood.storeor.entities.configurationposbystore.ResolutionEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IResolutionMapper {

    List<ResolutionEntity> resolutionDTOListToResolutionEntityList(List<ResolutionDTO> resolutionDTOS);

    ResolutionEntity resolutionDTOToResolutionEntity(ResolutionDTO resolutionDTO);
}
