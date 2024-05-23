package com.robinfood.localorderbc.usecases.getorderdatailbyidusecase;

import com.robinfood.localorderbc.entities.OrderEntity;
import com.robinfood.localorderbc.repositories.IOrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetOrderDetailByIdUseCaseTest {

    @Mock
    private IOrderRepository orderRepository;

    @InjectMocks
    private GetOrderDetailByIdUseCase getOrderDetailByIdUseCase;

    @Test
    void Get_Order_By_Id_Success() {
        when(orderRepository.getById(1L))
                .thenReturn(OrderEntity
                        .builder()
                        .id(1L)
                        .data("{}")
                        .build()
                );

        final String orderStringJson = this.getOrderDetailByIdUseCase.invoke(1L);

        Assertions.assertNotNull(orderStringJson);
    }


}