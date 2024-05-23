package com.robinfood.app.usecases.getorderdetailpaymentmethodbyorderids;

import com.robinfood.app.datamocks.dto.output.GetOrderDetailPaymentMethodDTOMock;
import com.robinfood.app.datamocks.entity.PaymentMethodEntityMock;
import com.robinfood.core.dtos.GetOrderDetailPaymentMethodDTO;
import com.robinfood.core.dtos.OrderPaymentDTO;
import com.robinfood.core.entities.PaymentMethodEntity;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.repository.paymentmethod.IPaymentMethodRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetOrderDetailPaymentMethodsByOrderIdsTest {

    @Mock
    private IPaymentMethodRepository paymentMethodRepository;

    @InjectMocks
    private GroupOrderDetailPaymentMethodsByOrderIds getOrderDetailPaymentMethodsByOrderIds;

    private final List<OrderPaymentDTO> orderPaymentDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderPaymentDTO(
                            null,
                            0.0,
                            1L,
                            1L,
                            1L,
                            11L,
                            8900.0,
                            0.0,
                            8900.0
                    )
            )
    );

    private final Map<Long, List<GetOrderDetailPaymentMethodDTO>> getOrderDetailPaymentMethodDTOMap = new HashMap<>();

    final List<GetOrderDetailPaymentMethodDTO> getOrderDetailPaymentMethodDTOS = List.of(
            GetOrderDetailPaymentMethodDTOMock.getDataDefault()
    );

    @Test
    void test_Invoke_Should_GetTheMethods_When_InvokeUseCase() {

        when(paymentMethodRepository.findAllById(anyList()))
                .thenReturn(List.of(PaymentMethodEntityMock.getDataDefault()));

        getOrderDetailPaymentMethodDTOMap.put(1L, getOrderDetailPaymentMethodDTOS);

        final Map<Long, List<GetOrderDetailPaymentMethodDTO>> result = getOrderDetailPaymentMethodsByOrderIds
                .invoke(orderPaymentDTOS);

        assertEquals(getOrderDetailPaymentMethodDTOMap, result);
    }

    @Test
    void test_Invoke_Should_ThrowGenericOrderBcException_When_PaymentMethodNotFound() {

        when(paymentMethodRepository.findAllById(anyList()))
                .thenReturn(List.of());

        Assertions.assertThrows(GenericOrderBcException.class, () -> {
            getOrderDetailPaymentMethodsByOrderIds
                    .invoke(orderPaymentDTOS);
        });
    }
}
