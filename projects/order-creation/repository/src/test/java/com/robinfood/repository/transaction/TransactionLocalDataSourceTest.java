package com.robinfood.repository.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.robinfood.core.entities.ConfigTransactionResponseEntity;
import com.robinfood.core.entities.transactionresponseentities.TransactionResponseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransactionLocalDataSourceTest {

    private TransactionLocalDataSource transactionLocalDataSource;

    private final ConfigTransactionResponseEntity configTransactionResponseEntity = new ConfigTransactionResponseEntity(
            TransactionResponseEntity.builder().build()
    );

    @BeforeEach
    void setup() {
        transactionLocalDataSource = new TransactionLocalDataSource();
    }

    @Test
    void test_Transaction_Ids_Are_Returned_Correctly() {
        transactionLocalDataSource.setTransactionResponse(configTransactionResponseEntity);

        final ConfigTransactionResponseEntity result = transactionLocalDataSource.getTransactionResponse();

        assertEquals(configTransactionResponseEntity, result);
    }

    @Test
    void test_Transaction_Constructor() {
        transactionLocalDataSource = new TransactionLocalDataSource(new ConfigTransactionResponseEntity());

        assertNotNull(transactionLocalDataSource);
    }
}
