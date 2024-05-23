package com.robinfood.storeor.mappers;

import com.robinfood.storeor.dtos.configurationposbystore.StoreResolutionsDTO;
import com.robinfood.storeor.entities.configurationposbystore.StoreResolutionsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IStoreResolutionsMapper {

    StoreResolutionsEntity storeResolutionDTOToStoreResolutionEntity(StoreResolutionsDTO storeResolutionsDTO);
}
