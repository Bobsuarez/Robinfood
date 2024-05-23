package com.robinfood.ordereports_bc_muyapp.usecases.gettransactionflowbyid;

import com.robinfood.app.library.exception.business.TransactionExecutionException;
import com.robinfood.ordereports_bc_muyapp.datamock.dto.ResponseOrderDetailDTOMock;
import com.robinfood.ordereports_bc_muyapp.datamock.dto.TransactionFlowDTOMock;
import com.robinfood.ordereports_bc_muyapp.datamock.entities.TransactionFlowEntityMock;
import com.robinfood.ordereports_bc_muyapp.dto.TransactionFlowDTO;
import com.robinfood.ordereports_bc_muyapp.models.mapper.TransactionFlowMapper;
import com.robinfood.ordereports_bc_muyapp.repository.orders.transactionflow.ITransactionFlowRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetTransactionFlowByIdUseCaseTest {

    @Mock
    private TransactionFlowMapper transactionFlowMappers;

    @Mock
    private ITransactionFlowRepository transactionFlowRepository;

    @InjectMocks
    private GetTransactionFlowByIdUseCase getTransactionFlowByIdUseCase;

    @Test
    void Given_Transaction_Id_Then_Return_Transaction() throws ExecutionException, InterruptedException {

        TransactionFlowDTO transactionFlowDTO = TransactionFlowDTOMock.getDataDefault();

        when(transactionFlowRepository.findTransactionFlowEntityByTransactionId(anyLong()))
                .thenReturn(TransactionFlowEntityMock.getDataDefault());

        when(transactionFlowMappers.toTransactionFlowDTO(any()))
                .thenReturn(TransactionFlowDTOMock.getDataDefault());

        CompletableFuture<Short> result =
                getTransactionFlowByIdUseCase.invoke(ResponseOrderDetailDTOMock.getDataDefault()
                                                             .getTransactionId());

        assertEquals(transactionFlowDTO.getFlowId(), result.get());
    }

    @Test
    void test_TransactionId_When_IsNull_Should_ApplicationException_Return() {

        when(transactionFlowRepository.findTransactionFlowEntityByTransactionId(anyLong()))
                .thenReturn(null);


        CompletableFuture<Short> future =
                getTransactionFlowByIdUseCase.invoke(ResponseOrderDetailDTOMock.getDataDefault()
                                                             .getTransactionId());

        ExecutionException exception = assertThrows(ExecutionException.class, future::get);
        assertInstanceOf(TransactionExecutionException.class, exception.getCause());
        assertEquals("Transaction creation failed", exception.getCause()
                .getMessage());
    }

}