package com.robinfood.app.datamocks.entity;

import com.robinfood.core.entities.TransactionFlowEntity;
import java.time.LocalDateTime;

public class TransactionFlowEntityMock {

    public static TransactionFlowEntity build() {
        return new TransactionFlowEntity(
            LocalDateTime.now(),
            1L,
            1L,
            1L,
            LocalDateTime.now()
        );
    }
}
