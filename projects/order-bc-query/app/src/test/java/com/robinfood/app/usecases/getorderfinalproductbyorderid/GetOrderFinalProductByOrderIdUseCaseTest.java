package com.robinfood.app.usecases.getorderfinalproductbyorderid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.robinfood.app.datamocks.entity.OrderFinalProductEntityMock;
import com.robinfood.core.dtos.OrderFinalProductDTO;
import com.robinfood.core.entities.OrderFinalProductEntity;
import com.robinfood.repository.orderfinalproducts.IOrderFinalProductRepository;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetOrderFinalProductByOrderIdUseCaseTest {

    @Mock
    private IOrderFinalProductRepository orderFinalProductRepository;

    @InjectMocks
    private GetOrderFinalProductByOrderIdUseCase useCase;

    @Test
    void get_final_products_by_order_id() {
        // Arrange
        Long orderId = 1L;

        OrderFinalProductEntity orderFinalProductEntity = new OrderFinalProductEntityMock()
            .getDataDefault();

        when(orderFinalProductRepository.findAllByOrderId(anyLong()))
            .thenReturn(Collections.singletonList(orderFinalProductEntity));

        // Act
        List<OrderFinalProductDTO> orderFinalProductDTOS = useCase.invoke(orderId);

        // Assert
        assertEquals(
            orderFinalProductEntity.getFinalProductId(),
            orderFinalProductDTOS.get(0).getFinalProductId()
        );
        assertEquals(
            orderFinalProductEntity.getFinalProductName(),
            orderFinalProductDTOS.get(0).getFinalProductName()
        );
        assertEquals(
            orderFinalProductEntity.getOrderId(),
            orderFinalProductDTOS.get(0).getOrderId()
        );
    }

}
