package com.robinfood.core.mappers;

import com.robinfood.core.entities.template.TransactionCreatedEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionCreatedMapperTest {

    @Test
    void test_TransactionCreatedMapper_Exception() {

        TransactionCreatedEntity transactionCreatedEntity = TransactionCreatedEntity.builder()
                .transactionUuid("1234")
                .requestTransaction("}{")
                .ttl(86400)
                .build();

        assertThrows(Exception.class,
                () -> TransactionCreatedMapper.buildTransactionCreatedDTO(transactionCreatedEntity)
        );
    }
}
