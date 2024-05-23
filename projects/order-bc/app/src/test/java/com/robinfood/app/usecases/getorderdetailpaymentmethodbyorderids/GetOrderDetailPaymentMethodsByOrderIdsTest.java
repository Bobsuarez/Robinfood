package com.robinfood.app.usecases.getorderdetailpaymentmethodbyorderids;

import com.robinfood.core.dtos.GetOrderDetailPaymentMethodDTO;
import com.robinfood.core.dtos.OrderPaymentDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GetOrderDetailPaymentMethodsByOrderIdsTest {

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
                            1L,
                            8900.0,
                            0.0,
                            8900.0
                    )
            )
    );

    private final Map<Long, List<GetOrderDetailPaymentMethodDTO>> getOrderDetailPaymentMethodDTOMap = new HashMap<>();

    final List<GetOrderDetailPaymentMethodDTO> getOrderDetailPaymentMethodDTOS = new ArrayList<>(
            Collections.singletonList(
                    new GetOrderDetailPaymentMethodDTO(
                            0.0,
                            1L,
                            8900.0,
                            1L,
                            1L,
                            0.0,
                            8900.0
                    )
            )
    );

    @Test
    void test_GetOrderDetailPaymentMethodsByOrderIds_Returns_Correctly() {

        getOrderDetailPaymentMethodDTOMap.put(1L, getOrderDetailPaymentMethodDTOS);

        final Map<Long, List<GetOrderDetailPaymentMethodDTO>> result = getOrderDetailPaymentMethodsByOrderIds
                .invoke(orderPaymentDTOS);

        assertEquals(getOrderDetailPaymentMethodDTOMap, result);
    }
}
