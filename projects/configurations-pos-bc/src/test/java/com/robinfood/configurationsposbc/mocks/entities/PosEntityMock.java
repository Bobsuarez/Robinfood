package com.robinfood.configurationsposbc.mocks.entities;

import com.robinfood.configurationsposbc.entities.PosEntity;

import java.time.LocalDateTime;

public class PosEntityMock {

    public PosEntity posEntity = PosEntity
            .builder()
            .id(1L)
            .code("POSCODE")
            .name("Pos Sale")
            .posTypeId(1L)
            .status(Boolean.TRUE)
            .createdAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .updatedAt(LocalDateTime.parse("2022-08-18T15:26:56.318967"))
            .build();
}
