package com.robinfood.app.usecases.sendordercreatedtoqueue;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.robinfood.app.datamocks.dto.input.RequestOrderTransactionDTOMock;
import com.robinfood.app.datamocks.dto.output.OutputCreatedOrderTransactionDTODataMock;
import com.robinfood.app.datamocks.queue.OrderCreatedQueueDTOMock;
import com.robinfood.app.mappers.queue.OrderCreatedMappers;
import com.robinfood.app.usecases.getorderpaymentbyorderids.IGetOrderPaymentByOrderIdsUseCase;
import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;
import com.robinfood.core.dtos.response.transaction.ResponseCreatedOrderTransactionDTO;
import com.robinfood.repository.queue.IProducerOrderRepository;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SendOrderCreatedToQueueUseCaseTest {

    @Mock
    private IGetOrderPaymentByOrderIdsUseCase getOrderPaymentByOrderIdsUseCase;

    @Mock
    private IProducerOrderRepository producerRepository;

    @Mock
    private OrderCreatedMappers orderCreatedMappers;

    @InjectMocks
    private SendOrderCreatedToQueueUseCase useCase;

    @Test
    void test_SendOrderCreatedToQueue_Happy_Path () {

        // Arrange
        RequestOrderTransactionDTO requestTransactionDTO =
            RequestOrderTransactionDTOMock.inputOrderTransactionValidatedDTO();

        ResponseCreatedOrderTransactionDTO responseCreatedOrderTransactionDTO =
            new OutputCreatedOrderTransactionDTODataMock().getDataDefault();

        when(orderCreatedMappers.toOrderCreatedDTOs(any(), any(), anyList())).thenReturn(
            Collections.singletonList(new OrderCreatedQueueDTOMock().orderCreated)
        );

        // Act
        useCase.invoke(requestTransactionDTO, responseCreatedOrderTransactionDTO);

        // Assert
        verify(orderCreatedMappers).toOrderCreatedDTOs(any(), any(), anyList());
        verify(producerRepository).sendOrderCreatedMessage(any());
    }

}
