package com.robinfood.app.consumers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.robinfood.app.mocks.queue.paymentmethod.order.OrderToCreateDTOMock;
import com.robinfood.app.usecases.processorderfromconsumer.ProcessOrderFromConsumerUseCase;
import com.robinfood.core.dtos.ordertocreatedto.OrderToCreateDTO;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.exceptions.TransactionCreationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class MessageConsumerTest {


    @InjectMocks
    private MessageConsumer messageConsumer;

    @Mock
    private ProcessOrderFromConsumerUseCase processOrderFromConsumerUseCase;

    @Test
    void test_processMessageConsumer_Should_ProcessMessageSuccessfully_When_ReceiveOrderToCreateDTO() {

        doNothing().when(processOrderFromConsumerUseCase)
            .invoke(any(OrderToCreateDTO.class), anyString(), anyString());

        OrderToCreateDTO orderToCreateDTO = OrderToCreateDTOMock.getDataDefault();

        assertAll(() -> messageConsumer.processMessageConsumer(orderToCreateDTO, "", ""));
        verify(processOrderFromConsumerUseCase, times(1)).invoke(any(OrderToCreateDTO.class),
            anyString(), anyString());

    }


    @Test
    void test_processMessageConsumer_Should_ThrowError_When_JmsException() {

        doThrow(TransactionCreationException.class)
            .when(processOrderFromConsumerUseCase)
            .invoke(any(OrderToCreateDTO.class), anyString(), anyString());

        OrderToCreateDTO orderToCreateDTO = OrderToCreateDTOMock.getDataDefault();

        assertThrows(Exception.class,
            () -> messageConsumer.processMessageConsumer(orderToCreateDTO, "", ""));

        verify(processOrderFromConsumerUseCase, times(1)).invoke(any(OrderToCreateDTO.class),
            anyString(), anyString());
    }

    @Test
    void test_processMessageConsumer_Should_ThrowError_When_JmsException_datanull() {

        TransactionCreationException ex = new TransactionCreationException(null, "Error obtain brands",
                TransactionCreationErrors.GET_BRANDS_ERROR,
                HttpStatus.PRECONDITION_FAILED);
        doThrow(ex)
                .when(processOrderFromConsumerUseCase)
                .invoke(any(OrderToCreateDTO.class), anyString(), anyString());

        OrderToCreateDTO orderToCreateDTO = OrderToCreateDTOMock.getDataDefault();
        assertThrows(Exception.class,
                () -> messageConsumer.processMessageConsumer(orderToCreateDTO, "", ""));

        verify(processOrderFromConsumerUseCase, times(1)).invoke(any(OrderToCreateDTO.class),
                anyString(), anyString());
    }
}
