package com.robinfood.storeor.mocks.entity.pos;

import com.robinfood.storeor.entities.PosEntity;
import com.robinfood.storeor.entities.PosTypeEntity;

public class PosEntityMock {

    public static PosEntity getDataDefault(boolean status){

        return PosEntity.builder()
                .id(1L)
                .code("poscode")
                .name("pos")
                .status(status)
                .posTypes(PosTypeEntity.builder()
                        .id(1L)
                        .name("pos prueba")
                        .build())
                .build();
    }
}
