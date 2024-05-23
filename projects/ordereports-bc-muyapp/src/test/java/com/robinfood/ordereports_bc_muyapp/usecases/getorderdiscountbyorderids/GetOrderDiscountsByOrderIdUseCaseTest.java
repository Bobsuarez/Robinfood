package com.robinfood.ordereports_bc_muyapp.usecases.getorderdiscountbyorderids;

import com.robinfood.ordereports_bc_muyapp.datamock.entities.OrderDiscountEntityMock;
import com.robinfood.ordereports_bc_muyapp.dto.OrderDiscountDTO;
import com.robinfood.ordereports_bc_muyapp.repository.orderdiscount.IOrderDiscountRepository;
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
class GetOrderDiscountsByOrderIdUseCaseTest {

    @Mock
    private IOrderDiscountRepository orderDiscountRepository;

    @InjectMocks
    private GetOrderDiscountsByOrderIdUseCase getOrderDiscountsByOrderIdUseCase;

    @Test
    void test_ValidatedDiscountByOrder_When_IsFull_Should_ListOrderDiscount_Return() throws ExecutionException, InterruptedException {

        when(orderDiscountRepository.findOrderDiscountEntitiesByOrderId(anyInt()))
                .thenReturn(Collections.singletonList(OrderDiscountEntityMock.getDataDefault()));

        CompletableFuture<List<OrderDiscountDTO>> orderDiscountDTOS = getOrderDiscountsByOrderIdUseCase.invoke(1);

        assertEquals(
                OrderDiscountEntityMock.getDataDefault()
                        .getId(),
                orderDiscountDTOS.get()
                        .get(0)
                        .getId()
        );
    }
}