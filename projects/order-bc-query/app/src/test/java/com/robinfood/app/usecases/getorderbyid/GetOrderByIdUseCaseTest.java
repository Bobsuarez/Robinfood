package com.robinfood.app.usecases.getorderbyid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.robinfood.app.datamocks.entity.OrderEntityMock;
import com.robinfood.core.dtos.OrderDTO;
import com.robinfood.core.entities.OrderEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.repository.orders.IOrdersRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetOrderByIdUseCaseTest {

    @Mock
    private IOrdersRepository ordersRepository;

    @InjectMocks
    private GetOrderByIdUseCase useCase;

    @Test
    void get_order_by_order_id_successfully() {

        // Arrange
        Long orderId = 1L;

        OrderEntity orderEntity = new OrderEntityMock().getDataDefault();

        when(ordersRepository.findById(anyLong())).thenReturn(Optional.of(orderEntity));

        // Act
        OrderDTO orderDTO = useCase.invoke(orderId);

        // Assert
        assertEquals(orderEntity.getBillingResolutionId(), orderDTO.getBillingResolutionId());
        assertEquals(orderEntity.getBrandName(), orderDTO.getBrandName());
        assertEquals(orderEntity.getCompanyId(), orderDTO.getCompanyId());
    }

    @Test
    void get_order_by_order_id_not_found() {

        // Arrange
        Long orderId = 1L;

        when(ordersRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Assert
        assertThrows(
            GenericOrderBcException.class,
            () -> useCase.invoke(orderId)
        );
    }

}
