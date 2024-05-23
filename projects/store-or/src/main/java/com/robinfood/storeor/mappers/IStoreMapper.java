package com.robinfood.storeor.mappers;

import com.robinfood.storeor.dtos.response.StoreResponseDTO;
import com.robinfood.storeor.entities.StoreEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IStoreMapper {

    StoreResponseDTO storeEntityToStoreResponseDto(StoreEntity storeEntity);

}
