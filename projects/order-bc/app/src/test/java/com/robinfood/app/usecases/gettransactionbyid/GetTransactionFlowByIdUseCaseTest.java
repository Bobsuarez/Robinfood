package com.robinfood.app.usecases.gettransactionbyid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.robinfood.app.datamocks.dto.core.TransactionFlowDTOMock;
import com.robinfood.app.datamocks.entity.TransactionFlowEntityMock;
import com.robinfood.app.mappers.TransactionFlowMappers;
import com.robinfood.core.dtos.TransactionFlowDTO;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.repository.transactionflow.ITransactionFlowRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetTransactionFlowByIdUseCaseTest {

    @Mock
    private TransactionFlowMappers transactionFlowMappers;

    @Mock
    private ITransactionFlowRepository transactionFlowRepository;

    @InjectMocks
    private GetTransactionFlowByIdUseCase useCase;

    @Test
    void Given_Transaction_Id_Then_Return_Transaction() {

        // Arrange
        Long transactionId = 1L;

        TransactionFlowDTO transactionFlowDTO = TransactionFlowDTOMock.build();

        when(transactionFlowRepository.findTransactionFlowEntityByTransactionId(anyLong()))
            .thenReturn(TransactionFlowEntityMock.build());

        when(transactionFlowMappers.toTransactionFlowDTO(any()))
            .thenReturn(transactionFlowDTO);

        // Act
        TransactionFlowDTO result = useCase.invoke(transactionId);

        //Assert
        assertEquals(transactionFlowDTO.getFlowId(), result.getFlowId());
        assertEquals(transactionFlowDTO.getId(), result.getId());
        assertEquals(transactionFlowDTO.getTransactionId(), result.getTransactionId());
    }

    @Test
    void Given_Transaction_Id_Then_Return_Not_Found() {

        // Arrange
        Long transactionId = 1L;

        when(transactionFlowRepository.findTransactionFlowEntityByTransactionId(anyLong()))
            .thenReturn(null);

        //Assert
        assertThrows(
            GenericOrderBcException.class,
            () -> useCase.invoke(transactionId)
        );
    }

}
