package com.robinfood.app.usecases.createtransaction;

import com.robinfood.core.dtos.request.transaction.RequestTransactionDTO;
import com.robinfood.core.dtos.response.transaction.ResponseTransactionDTO;
import com.robinfood.core.entities.TransactionEntity;
import com.robinfood.core.entities.TransactionFlowEntity;
import com.robinfood.repository.transaction.ITransactionCRUDRepository;
import com.robinfood.repository.transaction.ITransactionRepository;
import com.robinfood.repository.transactionflow.ITransactionFlowRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateTransactionUseCaseTest {

    @Mock
    private ITransactionCRUDRepository mockTransactionCRUDRepository;
    @Mock
    private ITransactionRepository mockTransactionRepository;

    @Mock
    private ITransactionFlowRepository mockTransactionFlowRepository;

    @InjectMocks
    private CreateTransactionUseCase createTransactionUseCase;

    private final TransactionEntity savedTransactionEntity = new TransactionEntity(
        null,
        1L,
        true,
        "2113a0e5-5196-457a-be72-2fa83b3bbbcb",
        null,
        1L,
        1000.0
        );

    private final TransactionEntity savedTransactionEntityWithCreatedAt = new TransactionEntity(
            LocalDateTime.now(),
            1L,
            true,
            "2113a0e5-5196-457a-be72-2fa83b3bbbcb",
            null,
            1L,
            1000.0
    );

    private final TransactionEntity transactionEntity = new TransactionEntity(
        null,
        1L,
        true,
        "2113a0e5-5196-457a-be72-2fa83b3bbbcb",
        null,
        1L,
        1000.0
    );

    private final RequestTransactionDTO transactionDTO = new RequestTransactionDTO(
        1L,
        1L,
        true,
        1000.0,
        "2113a0e5-5196-457a-be72-2fa83b3bbbcb",
        1L
    );

    private final TransactionEntity savedTransactionEntityIdNull = new TransactionEntity(
            null,
            null,
            true,
            "2113a0e5-5196-457a-be72-2fa83b3bbbcb",
            null,
            1L,
            1000.0
    );

    private final RequestTransactionDTO transactionDTOIdNull = new RequestTransactionDTO(
            null,
            1L,
            true,
            1000.0,
            "2113a0e5-5196-457a-be72-2fa83b3bbbcb",
            1L
    );

    private final TransactionFlowEntity transactionFlowEntity = new TransactionFlowEntity(
       null,
        1L,
        1L,
        1L,
        null
    );

    private final TransactionFlowEntity saveTransactionFlowEntity = new TransactionFlowEntity(
        1L,
        1L
    );

    @Test
    void test_CreateTransaction_When_Save_Success() {

        when(mockTransactionCRUDRepository.save(transactionEntity)).thenReturn(savedTransactionEntity);
        when(mockTransactionRepository.getLocalTransaction()).thenReturn(savedTransactionEntity);
        when(mockTransactionFlowRepository.save(any(TransactionFlowEntity.class))).thenReturn(saveTransactionFlowEntity);

        final ResponseTransactionDTO result = createTransactionUseCase.invoke(transactionDTO)
            .join();

        assertEquals(savedTransactionEntity.getId(), result.getId());
        assertEquals(savedTransactionEntity.getPaid(), result.getPaid());
        assertEquals(savedTransactionEntity.getUserId(), result.getUserId());
        assertEquals(savedTransactionEntity.getUniqueIdentifier(), result.getUniqueIdentifier());
        assertEquals(savedTransactionEntity.getValue(), result.getValue());
    }

    @Test
    void test_CreateTransaction_When_Created_At_Is_Not_Null_Success() {
        when(mockTransactionCRUDRepository
                .save(transactionEntity))
                .thenReturn(savedTransactionEntityWithCreatedAt);

        when(mockTransactionRepository.getLocalTransaction()).thenReturn(savedTransactionEntityWithCreatedAt);

        final ResponseTransactionDTO result = createTransactionUseCase.invoke(transactionDTO)
                .join();

        verify(mockTransactionRepository).setLocalTransaction(savedTransactionEntityWithCreatedAt);
        assertEquals(savedTransactionEntity.getPaid(), result.getPaid());
        assertEquals(savedTransactionEntity.getUserId(), result.getUserId());
        assertEquals(savedTransactionEntity.getUniqueIdentifier(), result.getUniqueIdentifier());
        assertEquals(savedTransactionEntity.getValue(), result.getValue());
    }

    @Test
    void test_CreateTransaction_When_Save_Id_Null_Success() {
        when(mockTransactionCRUDRepository.save(savedTransactionEntityIdNull)).thenReturn(savedTransactionEntity);
        when(mockTransactionRepository.getLocalTransaction()).thenReturn(savedTransactionEntity);

        final ResponseTransactionDTO result = createTransactionUseCase.invoke(transactionDTOIdNull)
                .join();

        assertEquals(savedTransactionEntity.getPaid(), result.getPaid());
        assertEquals(savedTransactionEntity.getUserId(), result.getUserId());
        assertEquals(savedTransactionEntity.getUniqueIdentifier(), result.getUniqueIdentifier());
        assertEquals(savedTransactionEntity.getValue(), result.getValue());
    }
}
