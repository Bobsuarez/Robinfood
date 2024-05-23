package com.robinfood.core.mappers;

import com.robinfood.core.dtos.configuration.StoreDTO;
import com.robinfood.core.dtos.configuration.StoreWithIdAndNameDTO;
import com.robinfood.core.entities.configurations.StoreEntity;

import java.util.List;
import java.util.stream.Collectors;

public final class StoreMappers {

    private StoreMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static StoreDTO storeEntityToStoreDTO(StoreEntity storeEntity) {

        return StoreDTO.builder()
                .id(storeEntity.getId())
                .name(storeEntity.getName())
                .location(storeEntity.getLocation())
                .phone(storeEntity.getPhone())
                .email(storeEntity.getEmail())
                .address(storeEntity.getAddress())
                .internalName(storeEntity.getInternalName())
                .identification(storeEntity.getIdentification())
                .timezone(storeEntity.getTimezone())
                .uuid(storeEntity.getUuid())
                .currencyType(storeEntity.getCurrencyType())
                .currencySymbol(storeEntity.getCurrencySymbol())
                .taxRegime(storeEntity.getTaxRegime())
                .multiBrand(StoreMultiBrandMappers.storeMultiBrandEntityToStoreMultiBrandEntity(
                        storeEntity.getMultiBrand())
                )
                .build();
    }

    public static List<StoreWithIdAndNameDTO> storesDtoToStoreWithIdAndNameDto(List<StoreDTO> storesDTO) {

        return storesDTO.stream().map(
                storeDTO -> StoreWithIdAndNameDTO.builder()
                        .id(storeDTO.getId())
                        .name(storeDTO.getName())
                        .build()
        ).collect(Collectors.toList());
    }
}
