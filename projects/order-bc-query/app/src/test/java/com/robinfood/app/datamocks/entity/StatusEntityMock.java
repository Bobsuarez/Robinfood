package com.robinfood.app.datamocks.entity;

import com.robinfood.core.entities.StatusEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class StatusEntityMock {

    public static StatusEntity getDataDefault() {

        return StatusEntity.builder()
                .createdAt(LocalDateTime.now())
                .code("st-001")
                .id(1L)
                .name("On its way")
                .order(BigDecimal.valueOf(1))
                .updatedAt(LocalDateTime.now())
                .parentId(1L)
                .build();
    }

    public static List<StatusEntity> getDataDefaultList() {

        return List.of(getDataDefault());
    }
}
