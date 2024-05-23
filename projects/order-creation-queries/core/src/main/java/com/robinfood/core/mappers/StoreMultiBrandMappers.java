package com.robinfood.core.mappers;

import com.robinfood.core.dtos.configuration.StoreMultiBrandDTO;
import com.robinfood.core.entities.configurations.StoreMultiBrandEntity;

public final class StoreMultiBrandMappers {

    private StoreMultiBrandMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static StoreMultiBrandDTO storeMultiBrandEntityToStoreMultiBrandEntity(
            StoreMultiBrandEntity storeMultiBrandEntity
    ) {

        return  StoreMultiBrandDTO.builder()
                .color(storeMultiBrandEntity.getColor())
                .image(storeMultiBrandEntity.getImage())
                .name(storeMultiBrandEntity.getName())
                .build();
    }
}
