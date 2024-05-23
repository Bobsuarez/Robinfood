package com.robinfood.storeor.mocks.dto;

import com.robinfood.storeor.entities.PosEntity;
import com.robinfood.storeor.entities.PosTypeEntity;

public class PosEntityMock {

    public static PosEntity getDataDefault() {

        return PosEntity.builder()
                .code("poscode")
                .id(1L)
                .name("pos")
                .posTypes(PosTypeEntity.builder()
                        .id(1L)
                        .name("pos prueba")
                        .build())
                .status(true)
                .build();
    }
    
}
