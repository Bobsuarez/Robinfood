package com.robinfood.repository.mocks;

import com.robinfood.core.entities.TransactionEntity;

import java.time.LocalDateTime;

public class TransactionEntityMocks {

    public TransactionEntity transactionEntity = new TransactionEntity(
            LocalDateTime.now(),
            1L,
            true,
            "123",
            null,
            1L,
            7900.0
    );
}
