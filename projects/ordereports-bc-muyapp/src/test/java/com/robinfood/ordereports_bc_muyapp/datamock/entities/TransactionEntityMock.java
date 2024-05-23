package com.robinfood.ordereports_bc_muyapp.datamock.entities;

import com.robinfood.ordereports_bc_muyapp.models.entities.TransactionEntity;

import java.util.UUID;

public class TransactionEntityMock {

    public static TransactionEntity getDataDefault() {
        return TransactionEntity
                .builder()
                .id(1)
                .userId(1L)
                .uniqueIdentifier(UUID.randomUUID()
                                          .toString())
                .build();
    }
}
