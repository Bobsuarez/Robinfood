package com.robinfood.localorderbc.usecases.ordercreateusecase;

import com.robinfood.localorderbc.dtos.OrderDTO;
import com.robinfood.localorderbc.entities.OrderEntity;
import com.robinfood.localorderbc.mappers.IOrderMapper;
import com.robinfood.localorderbc.repositories.IOrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderCreateUseCaseTest {

    @Mock
    private IOrderMapper orderMapper;

    @Mock
    private IOrderRepository orderRepository;

    @InjectMocks
    private OrderCreateUseCase orderCreateUseCase;

    @Test
    void Order_Create_Success() {

        when(orderMapper.orderDtoToOrderEntity(OrderDTO.builder().build()))
                .thenReturn(OrderEntity.builder().build());

        when(orderRepository.save(OrderEntity.builder().build()))
                .thenReturn(OrderEntity.builder().build());

        when(orderMapper.orderEntityToOrderDto(OrderEntity.builder().build()))
                .thenReturn(OrderDTO.builder().build());

        OrderDTO orderDTO = orderCreateUseCase.invoke(OrderDTO.builder().build());

        Assertions.assertNotNull(orderDTO);
    }

    @Test
    void Order_Update_Success() {
        Long orderId = 1L;
        Long statusId = 3L;

        OrderEntity orderEntity = OrderEntity.builder()
                                            .createdAt(LocalDateTime.now())
                                            .brandId(1L)
                                            .id(1L)
                                            .data("{}")
                                            .statusId(1L).build();

        OrderEntity updateOrderEntity = OrderEntity.builder()
                .createdAt(LocalDateTime.now())
                .brandId(1L)
                .id(1L)
                .data("{}")
                .statusId(3L).build();

        when(orderRepository.findById(orderId))
                .thenReturn(Optional.ofNullable(orderEntity));

        assert orderEntity != null;
        when(orderRepository.save(orderEntity))
                .thenReturn(updateOrderEntity);

        when(orderMapper.orderEntityToOrderDto(updateOrderEntity))
                .thenReturn(OrderDTO.builder().brandId(1L).id(1L).statusId(3L).build());

        OrderDTO orderDTO = orderCreateUseCase.update(orderId, statusId);

        Assertions.assertNotNull(orderDTO);
    }

    @Test
    void Order_Is_Not_Found() {
        Long orderId = 1L;
        Long statusId = 3L;
        Optional<OrderEntity> OrderEntityEmpty = Optional.empty();
        when(orderRepository.findById(orderId))
                .thenReturn(OrderEntityEmpty);

        OrderDTO orderDTO = orderCreateUseCase.update(orderId, statusId);

        Assertions.assertNull(orderDTO);
    }

}