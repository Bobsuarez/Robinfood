package com.robinfood.orderorlocalserver.usecases.processordercreatedfromconsumer;

import com.robinfood.orderorlocalserver.dtos.orderqueue.OrderCreatedQueueDTO;
import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailDTO;
import com.robinfood.orderorlocalserver.enums.Result;
import com.robinfood.orderorlocalserver.mocks.dtos.OrderDetailPrintDTOMock;
import com.robinfood.orderorlocalserver.repositories.sqs.OrderSqsRepository;
import com.robinfood.orderorlocalserver.usecases.getorderdetailprint.GetOrderDetailPrintUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProcessOrderCreatedFromConsumerUseCaseTest {

    @InjectMocks
    private ProcessOrderCreatedFromConsumerUseCase processOrderCreatedFromConsumerUseCase;

    @Mock
    private GetOrderDetailPrintUseCase getOrderDetailPrintUseCase;

    @Mock
    private OrderSqsRepository orderSqsRepository;

    List<Long> orderIds = Collections.singletonList(1L);

    final List<String> orderUids = new ArrayList<>();

    List<OrderDetailDTO> orderDetailDTOS = OrderDetailPrintDTOMock.getOrderDetailDTOS();

    OrderCreatedQueueDTO orderCreatedQueueDTO = OrderCreatedQueueDTO.builder()
            .id(1L)
            .orderDate("orderDate")
            .paid(true)
            .companyId(1L)
            .orderUid("uid")
            .discount(BigDecimal.ZERO)
            .flowId(1L)
            .orderNumber("orderNumber")
            .invoice(1L)
            .subtotal(BigDecimal.ZERO)
            .tax(BigDecimal.ZERO)
            .timeZone("timeZone")
            .total(BigDecimal.ZERO)
            .transactionId(1L)
            .build();

    @Test
    void test_process_order_created_from_consumer_ok() throws Exception {

        when(getOrderDetailPrintUseCase.invoke(orderIds, orderUids, List.of()))
                .thenReturn(new Result.Success<>(orderDetailDTOS));

        doNothing().when(orderSqsRepository)
                .sendOrderCreatedMessage(any(OrderDetailDTO.class));


        processOrderCreatedFromConsumerUseCase.invoke(orderCreatedQueueDTO);

        verify(
                orderSqsRepository,
                times(1)).sendOrderCreatedMessage(any(OrderDetailDTO.class)
        );

    }

    @Test
    void test_process_order_created_from_consumer_error() throws Exception {

        when(getOrderDetailPrintUseCase.invoke(orderIds, orderUids, List.of()))
                .thenReturn(new Result.Error(new Exception(), HttpStatus.NOT_FOUND));


        assertThrows(Exception.class,
                () -> processOrderCreatedFromConsumerUseCase.invoke(orderCreatedQueueDTO));

        verify(
                getOrderDetailPrintUseCase,
                times(1)
        ).invoke(orderIds, orderUids, Collections.emptyList());

    }

    @Test
    void test_process_order_created_from_consumer_not_paid() throws Exception {

        orderCreatedQueueDTO.setPaid(false);

        processOrderCreatedFromConsumerUseCase.invoke(orderCreatedQueueDTO);

        verifyNoInteractions(
                orderSqsRepository,
                getOrderDetailPrintUseCase
        );

    }

}