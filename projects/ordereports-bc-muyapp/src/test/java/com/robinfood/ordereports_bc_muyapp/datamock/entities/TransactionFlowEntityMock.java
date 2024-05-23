package com.robinfood.ordereports_bc_muyapp.datamock.entities;

import com.robinfood.ordereports_bc_muyapp.models.entities.TransactionFlowEntity;

import java.time.LocalDateTime;

public class TransactionFlowEntityMock {

    public static TransactionFlowEntity getDataDefault() {
        return TransactionFlowEntity.builder()
                .transactionId(1L)
                .flowId((short) 1)
                .id(1L)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
