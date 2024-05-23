package com.robinfood.app.usecases.getorderpaymentbyorderids;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.core.entities.OrderPaymentEntity;
import com.robinfood.repository.orderpayment.IOrderPaymentRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetOrderPaymentByOrderIdsUseCaseTest {

    @Mock
    private IOrderPaymentRepository orderPaymentRepository;

    @InjectMocks
    private GetOrderPaymentByOrderIdsUseCase getOrderPaymentByOrderIdsUseCase;

    private final List<Long> orderIds = new ArrayList<>(Arrays.asList(1L, 2L, 3L, 4L));

    private final List<OrderPaymentDTO> orderPaymentDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderPaymentDTO(
                            null,
                            0.0,
                            1L,
                            1L,
                            1L,
                            1L,
                            8900.0,
                            0.0,
                            8900.0
                    )
            )
    );

    private final List<OrderPaymentEntity> orderPaymentEntities = new ArrayList<>(
        Arrays.asList(
            new OrderPaymentEntity(
                LocalDateTime.now(),
                0.0,
                1L,
                1L,
                1L,
                1L,
                8900.0,
                0.0,
                LocalDateTime.now(),
                8900.0
            )
        )
    );

    @Test
    void test_GetOrderPaymentsByOrderId_Returns_Correctly() {

        when(orderPaymentRepository.findOrderPaymentEntitiesByOrderIdIn(orderIds))
            .thenReturn(orderPaymentEntities);

        final List<OrderPaymentDTO> result = getOrderPaymentByOrderIdsUseCase.invoke(orderIds);

        assertEquals(orderPaymentDTOS, result);
    }
}
