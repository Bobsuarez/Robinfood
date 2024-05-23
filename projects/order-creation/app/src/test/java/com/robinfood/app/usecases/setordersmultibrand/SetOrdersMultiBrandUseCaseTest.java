package com.robinfood.app.usecases.setordersmultibrand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SetOrdersMultiBrandUseCaseTest {

    @InjectMocks
    private SetOrdersMultiBrandUseCase useCase;

    @Test
    void given_orders_then_set_is_multi_brand_in_true() {
        // Arrange
        List<OrderDTO> orders = new TransactionRequestDTOMock().orderDTOsWithTwoProducts;

        // Act
        useCase.invoke(orders);

        // Assert
        assertEquals(Boolean.TRUE, orders.get(0).getIsMultiBrand());
    }

    @Test
    void given_orders_then_set_is_multi_brand_in_false() {
        // Arrange
        List<OrderDTO> orders = new TransactionRequestDTOMock().ordersDTOS;

        // Act
        useCase.invoke(orders);

        // Assert
        assertEquals(Boolean.FALSE, orders.get(0).getIsMultiBrand());
    }

}
