package com.robinfood.orderorlocalserver.consumers;

import com.robinfood.orderorlocalserver.dtos.orderqueue.OrderCreatedQueueDTO;
import com.robinfood.orderorlocalserver.exceptions.GetOrderDetailFailedException;
import com.robinfood.orderorlocalserver.usecases.processordercreatedfromconsumer.ProcessOrderCreatedFromConsumerUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderCreatedConsumerTest {

    @Mock
    private ProcessOrderCreatedFromConsumerUseCase processOrderCreatedFromConsumerUseCase;

    @InjectMocks
    private OrderCreatedConsumer orderCreatedConsumer;

    private final String message1 = "{\"id\": \"1234325\", \"store\":{\"id\":6,\"name\":\"MUY 116\"}}";

    @Test
    void test_order_created_consumer_should_process_message_success_fully_when_receive_order_to_create_and_order_store_is_tested()
            throws Exception
    {

        doNothing().when(processOrderCreatedFromConsumerUseCase)
                .invoke(any(OrderCreatedQueueDTO.class));

        orderCreatedConsumer.processMessageConsumer(message1);

        verify(
                processOrderCreatedFromConsumerUseCase,
                times(1)).invoke(any(OrderCreatedQueueDTO.class)
        );
    }

    @Test
    void test_order_created_consumer_should_throw_error_when_jmsException() throws GetOrderDetailFailedException {

        doThrow(GetOrderDetailFailedException.class)
                .when(processOrderCreatedFromConsumerUseCase)
                .invoke(any(OrderCreatedQueueDTO.class));

        orderCreatedConsumer.processMessageConsumer(message1);

        verify(
                processOrderCreatedFromConsumerUseCase,
                times(1)).invoke(any(OrderCreatedQueueDTO.class)
        );
    }
}