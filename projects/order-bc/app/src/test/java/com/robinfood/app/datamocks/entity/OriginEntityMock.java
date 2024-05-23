package com.robinfood.app.datamocks.entity;

import com.robinfood.core.entities.OriginEntity;

import java.time.LocalDateTime;

public class OriginEntityMock {

    public static OriginEntity build() {
        return OriginEntity.builder()
                .code("Rappi")
                .color("F0F0F0")
                .id(1L)
                .name("Rappi")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

}
