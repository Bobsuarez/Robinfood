package com.robinfood.ordereports_bc_muyapp.usecases.getorderpaymentbyorderids;

import com.robinfood.ordereports_bc_muyapp.datamock.entities.OrderEntityMock;
import com.robinfood.ordereports_bc_muyapp.datamock.entities.OrderPaymentEntityMock;
import com.robinfood.ordereports_bc_muyapp.dto.OrderPaymentDTO;
import com.robinfood.ordereports_bc_muyapp.repository.orderpayment.IOrderPaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetOrderPaymentByOrderIdUseCaseTest {

    @Mock
    private IOrderPaymentRepository orderPaymentRepository;

    @InjectMocks
    private GetOrderPaymentByOrderIdUseCase getOrderPaymentByOrderIdUseCase;

    @Test
    void test_ValidatedOrderPayment_When_IsFull_Should_ListOrderPayment_Return() throws ExecutionException, InterruptedException {

        when(orderPaymentRepository.findAllByOrderId(anyInt()))
                .thenReturn(Collections.singletonList(OrderPaymentEntityMock.getDataDefault()));

        CompletableFuture<List<OrderPaymentDTO>> orderPaymentDTOS =
                getOrderPaymentByOrderIdUseCase.invoke(OrderEntityMock.getDataDefault()
                                                               .getId());

        assertEquals(
                OrderPaymentEntityMock.getDataDefault()
                        .getDiscount(),
                orderPaymentDTOS.get()
                        .get(0)
                        .getDiscount()
        );

        assertEquals(
                OrderPaymentEntityMock.getDataDefault()
                        .getSubtotal(),
                orderPaymentDTOS.get()
                        .get(0)
                        .getSubtotal()
        );

        assertEquals(
                OrderPaymentEntityMock.getDataDefault()
                        .getTax(),
                orderPaymentDTOS.get()
                        .get(0)
                        .getTax()
        );
    }
}