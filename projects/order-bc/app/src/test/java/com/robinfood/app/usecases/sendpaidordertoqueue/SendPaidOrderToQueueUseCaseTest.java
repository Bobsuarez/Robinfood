package com.robinfood.app.usecases.sendpaidordertoqueue;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.robinfood.app.datamocks.dto.core.OrderAddressDTOMock;
import com.robinfood.app.datamocks.dto.core.OrderDTOMock;
import com.robinfood.app.datamocks.dto.core.OrderDiscountDTOMock;
import com.robinfood.app.mappers.queue.OrderPaidToQueueMappers;
import com.robinfood.app.usecases.getorderaddressbyorderid.IGetOrderAddressByOrderIdUseCase;
import com.robinfood.app.usecases.getorderbyid.IGetOrderByIdUseCase;
import com.robinfood.app.usecases.getorderdiscountbyorderids.IGetOrderDiscountsByOrderIdUseCase;
import com.robinfood.app.usecases.getorderfinalproductbyorderid.IGetOrderFinalProductByOrderIdUseCase;
import com.robinfood.app.usecases.getorderpaymentbyorderids.IGetOrderPaymentByOrderIdUseCase;
import com.robinfood.app.usecases.getpickuptimebytransactionid.IGetPickupTimesByTransactionIdUseCase;
import com.robinfood.app.usecases.gettransactionbyid.IGetTransactionFlowByIdUseCase;
import com.robinfood.app.usecases.getuserdatalistbyuserid.IGetUserDataByOrderIdUseCase;
import com.robinfood.repository.queue.IProducerOrderRepository;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SendPaidOrderToQueueUseCaseTest {

    @Mock
    private IGetOrderByIdUseCase getOrderByIdUseCase;

    @Mock
    private IGetOrderDiscountsByOrderIdUseCase getOrderDiscountsByOrderIdUseCase;

    @Mock
    private IGetOrderAddressByOrderIdUseCase getOrderAddressByOrderIdUseCase;

    @Mock
    private IGetOrderFinalProductByOrderIdUseCase getOrderFinalProductByOrderIdUseCase;

    @Mock
    private IGetUserDataByOrderIdUseCase getUserDataByOrderIdUseCase;

    @Mock
    private IGetOrderPaymentByOrderIdUseCase getOrderPaymentByOrderIdUseCase;

    @Mock
    private IGetPickupTimesByTransactionIdUseCase getPickupTimesByTransactionIdUseCase;

    @Mock
    private OrderPaidToQueueMappers orderPaidToQueueMappers;

    @Mock
    private IProducerOrderRepository producerOrderRepository;

    @Mock
    private IGetTransactionFlowByIdUseCase getTransactionFlowByIdUseCase;

    @InjectMocks
    private SendPaidOrderToQueueUseCase useCase;

    @Test
    void send_message_with_paid_order() {

        // Arrange
        Long orderId = 1L;

        when(getOrderByIdUseCase.invoke(any())).thenReturn(OrderDTOMock.build());

        when(getOrderDiscountsByOrderIdUseCase.invoke(any()))
            .thenReturn(Collections.singletonList(OrderDiscountDTOMock.build()));

        when(getOrderAddressByOrderIdUseCase.invoke(any())).thenReturn(OrderAddressDTOMock.build());

        // Act
        useCase.invoke(orderId);

        // Assert
        verify(getOrderByIdUseCase).invoke(anyLong());
        verify(getOrderDiscountsByOrderIdUseCase).invoke(anyLong());
        verify(getOrderAddressByOrderIdUseCase).invoke(anyLong());
        verify(getOrderFinalProductByOrderIdUseCase).invoke(anyLong());
        verify(getUserDataByOrderIdUseCase).invoke(anyLong());
        verify(getOrderPaymentByOrderIdUseCase).invoke(anyLong());
        verify(getPickupTimesByTransactionIdUseCase).invoke(anyLong());
        verify(getTransactionFlowByIdUseCase).invoke(anyLong());
        verify(orderPaidToQueueMappers).toOrderCreatedQueueDTO(
            any(), any(), anyList(), any(), anyList(), anyList(), anyList(), any()
        );
        verify(producerOrderRepository).sendOrderCreatedMessage(any());
    }

}
