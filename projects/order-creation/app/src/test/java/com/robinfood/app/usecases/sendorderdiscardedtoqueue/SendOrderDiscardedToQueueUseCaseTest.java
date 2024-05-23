package com.robinfood.app.usecases.sendorderdiscardedtoqueue;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.repository.queue.activemq.IProducerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SendOrderDiscardedToQueueUseCaseTest {

    @Mock
    private IProducerRepository mockProducerRepository;

    @InjectMocks
    private SendOrderDiscardedToQueueUseCase sendOrderDiscardedToQueueUseCase;

    private final TransactionRequestDTO mockTransactionRequest = new TransactionRequestDTOMock().transactionRequestDTO;

    @Test
    void test_Send_Order_Discarded_Happy_Path() {

        // Arrange
        TransactionRequestDTO transactionRequestDTO = mockTransactionRequest;
        transactionRequestDTO.setId(1L);
        transactionRequestDTO.getOrders().get(0).setId(1L);

        // Act
        TransactionRequestDTO response = sendOrderDiscardedToQueueUseCase.invoke(transactionRequestDTO);

        // Assert
        assertEquals(mockTransactionRequest, response);
    }

    @Test
    void test_Send_Order_Discarded_Transaction_Id_Equal_Null() {

        // Act
        TransactionRequestDTO response = sendOrderDiscardedToQueueUseCase.invoke(mockTransactionRequest);

        // Assert
        assertEquals(mockTransactionRequest, response);
    }
}
