package com.robinfood.storeor.mappers;

import com.robinfood.storeor.dtos.configurationposbystore.ActivateOrDeactivateDTO;
import com.robinfood.storeor.entities.configurationposbystore.ActivateOrDeactivateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IActiveResolutionMapper {

    ActivateOrDeactivateEntity activeResolutionDTOActiveResolutionEntity(
            ActivateOrDeactivateDTO activateOrDeactivateDTO);
}
