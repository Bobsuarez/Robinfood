package com.robinfood.repository.transaction;

import com.robinfood.core.entities.TransactionEntity;
import com.robinfood.repository.mocks.TransactionEntityMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionRepositoryTest {

    @Mock
    private ITransactionLocalDatasource mockTransactionLocalDatasource;

    @InjectMocks
    private TransactionRepository transactionRepository;

    private final TransactionEntityMocks transactionEntityMocks = new TransactionEntityMocks();

    @Test
    void test_Set_Local_Transaction() {
        doNothing().when(mockTransactionLocalDatasource)
                .setLocalTransactionEntity(transactionEntityMocks.transactionEntity);

        transactionRepository.setLocalTransaction(transactionEntityMocks.transactionEntity);

        verify(mockTransactionLocalDatasource)
                .setLocalTransactionEntity(transactionEntityMocks.transactionEntity);
    }

    @Test
    void test_Get_Local_Transaction() {
        when(mockTransactionLocalDatasource.getCurrentTransactionStored()).thenReturn(
                transactionEntityMocks.transactionEntity
        );

        final TransactionEntity result = transactionRepository.getLocalTransaction();

        verify(mockTransactionLocalDatasource).getCurrentTransactionStored();
        assertEquals(transactionEntityMocks.transactionEntity, result);
    }
}
