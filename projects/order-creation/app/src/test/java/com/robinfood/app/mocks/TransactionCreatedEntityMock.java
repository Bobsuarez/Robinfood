package com.robinfood.app.mocks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.core.entities.template.TransactionCreatedEntity;

import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

public class TransactionCreatedEntityMock {

    private static final TransactionRequestDTOMock transactionRequestDTOMocks = new TransactionRequestDTOMock();

    public static TransactionCreatedEntity build() throws JsonProcessingException {
        return TransactionCreatedEntity.builder()
            .transactionUuid(transactionRequestDTOMocks.transactionRequestDTO.getUuid().toString())
            .requestTransaction(objectToJson(transactionRequestDTOMocks.transactionRequestDTO))
            .ttl(86400)
            .build();
    }

    public static TransactionCreatedEntity buildFail() {
        return TransactionCreatedEntity.builder()
            .transactionUuid("1234")
            .requestTransaction("}{")
            .ttl(86400)
            .build();
    }
}
