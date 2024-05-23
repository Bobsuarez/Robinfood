package com.robinfood.localserver.commons.mappers.storeconfiguration;

import com.robinfood.localserver.commons.dtos.storeconfiguration.StoreDTO;
import com.robinfood.localserver.commons.entities.storeconfiguration.StoreEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IStoreConfigurationMapper {

    StoreDTO storeEntityToStoreDto(StoreEntity storeEntity);

    StoreEntity storeDTOToStoreEntity(StoreDTO storeDTO);

}
