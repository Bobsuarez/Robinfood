package com.robinfood.storeor.mappers;

import com.robinfood.storeor.dtos.findAllResolutions.SearchResolutionDTO;
import com.robinfood.storeor.dtos.response.DataResolutionResponseDTO;
import com.robinfood.storeor.entities.configurationposbystore.DataResolutionEntity;
import com.robinfood.storeor.entities.configurationposbystore.SearchResolutionEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface IFindAllResolutionsMapper {

    SearchResolutionEntity searchResolutionEntityToResolutionsListResponseDTO(SearchResolutionDTO searchResolutionDTO);

    DataResolutionResponseDTO dataResolutionEntityToDataResolutionResponseDTO(
            DataResolutionEntity dataResolutionEntity);
}
