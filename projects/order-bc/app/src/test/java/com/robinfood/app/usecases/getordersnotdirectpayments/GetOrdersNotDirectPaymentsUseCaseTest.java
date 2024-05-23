package com.robinfood.app.usecases.getordersnotdirectpayments;

import com.robinfood.app.datamocks.dto.core.OrderDTOMock;
import com.robinfood.app.datamocks.dto.core.OrderPaymentDTOMock;
import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.core.utilities.PaymentMethodsUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetOrdersNotDirectPaymentsUseCaseTest {

    @Mock
    private PaymentMethodsUtil paymentMethodsUtil;

    @InjectMocks
    private GetOrdersNotDirectPaymentsUseCase getOrdersNotDirectPaymentsUseCase;

    @Test
    void test_invoke_Should_ReturnOrders_When_InvokeUseCase() {

        when(paymentMethodsUtil.getPaymentMethodIds())
                .thenReturn(List.of(4L));

        getOrdersNotDirectPaymentsUseCase.invoke(
                List.of(OrderDTOMock.build()),
                List.of(OrderPaymentDTOMock.getDataDefault())
        );

        verify(paymentMethodsUtil)
                .getPaymentMethodIds();
    }

    @Test
    void test_invoke_Should_NotReturnOrders_When_InvokeUseCase() {

        OrderPaymentDTO orderPaymentDTO = OrderPaymentDTOMock.getDataDefault();
        orderPaymentDTO.setOrderId(10L);

        when(paymentMethodsUtil.getPaymentMethodIds())
                .thenReturn(List.of(4L));

        getOrdersNotDirectPaymentsUseCase.invoke(
                List.of(OrderDTOMock.build()),
                List.of(orderPaymentDTO)
        );

        verify(paymentMethodsUtil)
                .getPaymentMethodIds();
    }

}