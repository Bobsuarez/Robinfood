package com.robinfood.localorderbc.usecases.orderupdateusecase;

import com.robinfood.localorderbc.entities.OrderEntity;
import com.robinfood.localorderbc.repositories.IOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderUpdateUseCaseTest {

    @Mock
    private IOrderRepository orderRepository;

    @InjectMocks
    private OrderUpdateUseCase orderUpdateUseCase;

    @Test
    void invoke_WhenOrderExists_ShouldUpdateOrderData() {

        Long orderId = 1L;
        String orderJson = "{\"id\": 1, \"name\": \"Example Order\"}";
        OrderEntity existingOrder = OrderEntity
                .builder()
                .id(1L)
                .build();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));

        orderUpdateUseCase.invoke(orderId, orderJson);

        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, times(1)).save(existingOrder);
        assertEquals(orderJson, existingOrder.getData());
    }

    @Test
    void invoke_WhenOrderDoesNotExist_ShouldNotUpdateOrderData() {

        Long orderId = 1L;
        String orderJson = "{\"id\": 1, \"name\": \"Example Order\"}";
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        orderUpdateUseCase.invoke(orderId, orderJson);

        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, never()).save(any());
    }
}