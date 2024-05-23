package com.robinfood.storeor.mappers;

import com.robinfood.storeor.dtos.configurationposbystore.ActivateOrDeactivatePosDTO;
import com.robinfood.storeor.entities.configurationposbystore.ActivateOrDeactivatePosEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IActiveOrDeactivatePosMapper {

    ActivateOrDeactivatePosEntity activeOrDeactivatePosDTOActiveOrDeactivateEntity(
            ActivateOrDeactivatePosDTO activateOrDeactivatePosDTO);
}
