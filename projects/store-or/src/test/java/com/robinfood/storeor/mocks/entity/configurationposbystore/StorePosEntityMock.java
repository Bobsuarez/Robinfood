package com.robinfood.storeor.mocks.entity.configurationposbystore;

import com.robinfood.storeor.entities.configurationposbystore.StorePosEntity;

import java.util.Collections;
import java.util.List;

public class StorePosEntityMock {

    public List<StorePosEntity> storePosEntities = List.of(
            StorePosEntity.builder()
                    .id(1L)
                    .name("caja1")
                    .resolutions(Collections.emptyList())
                    .build(),
            StorePosEntity.builder()
                    .id(2L)
                    .name("caja2")
                    .resolutions(Collections.emptyList())
                    .build()
    );
}
