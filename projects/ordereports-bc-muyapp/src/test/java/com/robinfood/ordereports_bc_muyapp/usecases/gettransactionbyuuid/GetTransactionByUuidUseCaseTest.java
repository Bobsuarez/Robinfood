package com.robinfood.ordereports_bc_muyapp.usecases.gettransactionbyuuid;

import com.robinfood.app.library.exception.business.TransactionExecutionException;
import com.robinfood.ordereports_bc_muyapp.datamock.entities.TransactionEntityMock;
import com.robinfood.ordereports_bc_muyapp.repository.transaction.ITransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class GetTransactionByUuidUseCaseTest {

    @Mock
    private ITransactionRepository transactionRepository;

    @InjectMocks
    private GetTransactionByUuidUseCase getTransactionByUuidUseCase;

    @Test
    void test_GetTransactionByUuidUseCase_When_IsPresent_Should_TransactionId_Return()
            throws ExecutionException, InterruptedException {

        Mockito.when(transactionRepository.findByUniqueIdentifier(anyString()))
                .thenReturn(TransactionEntityMock.getDataDefault());

        final CompletableFuture<Integer> resultOrderStatusDTO =
                getTransactionByUuidUseCase.invoke(UUID.randomUUID()
                                                           .toString());

        assertEquals(resultOrderStatusDTO.get(), 1);
    }

    @Test
    void test_ValidatedListOrderEntity_When_IsNull_Should_TransactionId_Return() {

        when(transactionRepository.findByUniqueIdentifier(anyString()))
                .thenReturn(null);

        // Act & Assert
        CompletableFuture<Integer> future = getTransactionByUuidUseCase.invoke(UUID.randomUUID()
                                                                                       .toString());
        ExecutionException exception = assertThrows(ExecutionException.class, future::get);
        assertInstanceOf(TransactionExecutionException.class, exception.getCause());
        assertEquals("Transaction creation failed", exception.getCause()
                .getMessage());
    }
}