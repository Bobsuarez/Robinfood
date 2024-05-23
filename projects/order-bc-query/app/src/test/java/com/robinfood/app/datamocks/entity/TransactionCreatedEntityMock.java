package com.robinfood.app.datamocks.entity;

import com.robinfood.core.entities.TransactionCreatedEntity;

public class TransactionCreatedEntityMock {

    public TransactionCreatedEntity build() {
        return TransactionCreatedEntity.builder()
            .transactionId(1234L)
            .requestTransaction("\"company\": {\n" +
                "                \"currency\": \"BRL\",\n" +
                "                \"id\": 5\n" +
                "            }")
            .ttl(86400)
            .build();
    }

    public TransactionCreatedEntity buildFail() {
        return TransactionCreatedEntity.builder()
            .transactionId(1234L)
            .requestTransaction("}{")
            .ttl(86400)
            .build();
    }
}
