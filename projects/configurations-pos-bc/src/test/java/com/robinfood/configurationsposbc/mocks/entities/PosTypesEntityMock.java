package com.robinfood.configurationsposbc.mocks.entities;

import com.robinfood.configurationsposbc.entities.PosTypesEntity;

import java.time.LocalDateTime;

public class PosTypesEntityMock {

    public PosTypesEntity posTypesEntity = PosTypesEntity
            .builder()
            .id(1L)
            .name("Pos Type")
            .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .build();
}
