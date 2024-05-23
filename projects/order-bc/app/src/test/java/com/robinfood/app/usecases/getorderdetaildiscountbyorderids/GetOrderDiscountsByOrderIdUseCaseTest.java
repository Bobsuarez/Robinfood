package com.robinfood.app.usecases.getorderdetaildiscountbyorderids;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.robinfood.app.datamocks.entity.OrderDiscountEntityMock;
import com.robinfood.app.usecases.getorderdiscountbyorderids.GetOrderDiscountsByOrderIdUseCase;
import com.robinfood.core.dtos.OrderDiscountDTO;
import com.robinfood.core.entities.OrderDiscountEntity;
import com.robinfood.repository.orderdiscount.IOrderDiscountCRUDRepository;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetOrderDiscountsByOrderIdUseCaseTest {

    @Mock
    private IOrderDiscountCRUDRepository orderDiscountCRUDRepository;

    @InjectMocks
    private GetOrderDiscountsByOrderIdUseCase useCase;

    @Test
    void get_order_discounts_by_order_id() {

        // Arrange
        Long orderId = 1L;

        OrderDiscountEntity orderDiscountEntity = OrderDiscountEntityMock.build();

        when(orderDiscountCRUDRepository.findOrderDiscountEntitiesByOrderId(anyLong()))
            .thenReturn(Collections.singletonList(orderDiscountEntity));

        // Act
        List<OrderDiscountDTO> orderDiscountDTOS = useCase.invoke(orderId);

        // Assert
        assertEquals(orderDiscountEntity.getDiscountId(), orderDiscountDTOS.get(0).getDiscountId());
        assertEquals(orderDiscountEntity.getOrderId(), orderDiscountDTOS.get(0).getOrderId());
        assertEquals(orderDiscountEntity.getDiscountValue(), orderDiscountDTOS.get(0).getDiscountValue());
    }

}
