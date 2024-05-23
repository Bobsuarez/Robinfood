package com.robinfood.app.usecases.getorderpaymentbyorderids;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.robinfood.app.datamocks.entity.OrderPaymentEntityMock;
import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.core.entities.OrderPaymentEntity;
import com.robinfood.repository.orderpayment.IOrderPaymentRepository;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetOrderPaymentByOrderIdUseCaseTest {

    @Mock
    private IOrderPaymentRepository orderPaymentRepository;

    @InjectMocks
    private GetOrderPaymentByOrderIdUseCase useCase;

    @Test
    void get_payment_methods_by_order_id() {
        // Arrange
        Long orderId = 1L;

        OrderPaymentEntity orderPaymentEntity = OrderPaymentEntityMock.build();

        when(orderPaymentRepository.findAllByOrderId(anyLong()))
            .thenReturn(Collections.singletonList(orderPaymentEntity));

        // Act
        List<OrderPaymentDTO> orderPaymentDTOS = useCase.invoke(orderId);

        // Assert
        assertEquals(orderPaymentEntity.getDiscount(), orderPaymentDTOS.get(0).getDiscount());
        assertEquals(orderPaymentEntity.getSubtotal(), orderPaymentDTOS.get(0).getSubtotal());
        assertEquals(orderPaymentEntity.getTax(), orderPaymentDTOS.get(0).getTax());
    }

}
