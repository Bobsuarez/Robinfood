package com.robinfood.app.usecases.getorderdetaildiscountbyorderids;

import com.robinfood.core.dtos.OrderDiscountDTO;
import com.robinfood.core.dtos.GetOrderDetailDiscountDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GetDetailOrderDiscountByOrderIdsUseCaseTest {

    @InjectMocks
    private GroupOrderDetailDiscountByOrderIdsUseCase getDetailOrderDiscountByOrderIdsUseCase;

    private final List<GetOrderDetailDiscountDTO> getOrderDetailDiscountDTOS = new ArrayList<>(Arrays.asList(
            new GetOrderDetailDiscountDTO(
                    1L,
                    1L,
                    null,
                    1L,
                    5000.0
            ),
            new GetOrderDetailDiscountDTO(
                    2L,
                    1L,
                    null,
                    1L,
                    1000.0
            )
    ));

    private final Map<Long, List<GetOrderDetailDiscountDTO>> getDetailOrderDiscountByOrderIdsDTOS = new HashMap<>() {{
        put(1L, getOrderDetailDiscountDTOS);
    }};

    private final List<OrderDiscountDTO> orderDiscountDTOS = new ArrayList<>(Arrays.asList(
            new OrderDiscountDTO(
                    1L,
                    5000.0,
                    1L,
                    1L,
                    1L,
                    null
            ),
            new OrderDiscountDTO(
                    2L,
                    1000.0,
                    2L,
                    1L,
                    1L,
                    null
            )
    ));

    @Test
    void test_GetDetailOrderDiscountByOrderIds_Returns_Correctly() {

        final Map<Long, List<GetOrderDetailDiscountDTO>> result = getDetailOrderDiscountByOrderIdsUseCase
                .invoke(orderDiscountDTOS);

        assertEquals(getDetailOrderDiscountByOrderIdsDTOS, result);
    }
}
