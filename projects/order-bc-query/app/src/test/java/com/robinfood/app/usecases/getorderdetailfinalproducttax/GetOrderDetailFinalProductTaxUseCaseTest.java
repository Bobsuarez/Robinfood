package com.robinfood.app.usecases.getorderdetailfinalproducttax;

import com.robinfood.core.dtos.GetOrderDetailFinalProductTaxDTO;
import com.robinfood.core.dtos.OrderProductTaxDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GetOrderDetailFinalProductTaxUseCaseTest {

    @InjectMocks
    private GetOrderDetailFinalProductTaxUseCase getOrderDetailFinalProductTaxUseCase;

    private final List<OrderProductTaxDTO> orderProductTaxDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderProductTaxDTO(
                            1L,
                            1L,
                            1L,
                            1L,
                            1L,
                            1L,
                            1L,
                            0.0,
                            1L,
                            "Impoconsumo",
                            0.0
                    )
            )
    );

    private final Map<Long, List<GetOrderDetailFinalProductTaxDTO>> getOrderDetailFinalProductTaxDTOMap = new HashMap<>();

    final List<GetOrderDetailFinalProductTaxDTO> getOrderDetailFinalProductTaxDTOS = new ArrayList<>(
            Collections.singletonList(
                    new GetOrderDetailFinalProductTaxDTO(
                            1L,
                            1L,
                            1L,
                            "Impoconsumo",
                            0.0,
                            1L,
                            0.0,
                            1L
                    )
            )
    );

    @Test
    void test_GetOrderDetailFinalProductTax_Returns_Correctly() {

        getOrderDetailFinalProductTaxDTOMap.put(1L, getOrderDetailFinalProductTaxDTOS);

        final Map<Long, List<GetOrderDetailFinalProductTaxDTO>> result = getOrderDetailFinalProductTaxUseCase
                .invoke(orderProductTaxDTOS);

        assertEquals(getOrderDetailFinalProductTaxDTOMap, result);
    }
}
