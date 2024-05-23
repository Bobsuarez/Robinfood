package com.robinfood.ordereports_bc_muyapp.datamock.entities;

import com.robinfood.ordereports_bc_muyapp.models.entities.StatusEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class StatusEntityMock {

    public static StatusEntity getDataDefault() {
        return StatusEntity.builder()
                .createdAt(LocalDateTime.now())
                .code("ACTIVE")
                .id((short) 1)
                .name("Active Status")
                .order(new BigDecimal("1.0"))
                .updatedAt(LocalDateTime.now())
                .parentId((short) 0)
                .build();
    }
}