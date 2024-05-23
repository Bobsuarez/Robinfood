package com.robinfood.app.usecases.getorderdetailfinalproductgroup;

import com.robinfood.app.usecases.getorderdetailfinalproductportion.IGetOrderDetailPortionsByProductIdsUseCase;
import com.robinfood.app.usecases.getorderremovedportionsbyproductid.IGetOrderRemovedPortionsByProductIdUseCase;
import com.robinfood.core.dtos.GetOrderDetailFinalProductGroupDTO;
import com.robinfood.core.dtos.GetOrderDetailFinalProductPortionDTO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetOrderDetailGroupWithPortionsByProductIdsUseCaseTest {

    @Mock
    private IGetOrderDetailPortionsByProductIdsUseCase mockGetOrderDetailFinalProductPortionByGroupIdUseCase;

    @Mock
    private IGetOrderRemovedPortionsByProductIdUseCase mockGetOrderRemovedPortionsByProductIdUseCase;

    @InjectMocks
    private GetOrderDetailGroupWithPortionsByProductIdsUseCase getOrderDetailGroupWithPortionsByProductIdsUseCase;

    private final List<Long> orderFinalProductIds = new ArrayList<>(Arrays.asList(1L, 2L, 3L));

    private final Map<Long, List<GetOrderDetailFinalProductPortionDTO>> getOrderDetailFinalProductPortionDTOMap = new HashMap<>();

    private final List<GetOrderDetailFinalProductPortionDTO> getOrderDetailFinalProductPortionDTOS = new ArrayList<>(
            Collections.singletonList(
                    new GetOrderDetailFinalProductPortionDTO(
                            false,
                            null,
                            BigDecimal.ZERO,
                            1L,
                            1L,
                            "ingredientes",
                            1L,
                            "Arroz",
                            1L,
                            8900.0,
                            1L,
                            1,
                            1,
                            "sku",
                            1L,
                            1.0
                    )
            )
    );

    private final Map<Long, List<GetOrderDetailFinalProductGroupDTO>> getOrderDetailFinalProductGroupDTOMap = new HashMap<>();

    private final List<GetOrderDetailFinalProductGroupDTO> getOrderDetailFinalProductGroupDTO = new ArrayList<>(
            Collections.singletonList(
                    new GetOrderDetailFinalProductGroupDTO(
                            1L,
                            1L,
                            "ingredientes",
                            1L,
                            getOrderDetailFinalProductPortionDTOS,
                            new ArrayList<>(),
                            "sku"
                    )
            )
    );

    @Test
    void test_GetOrderDetailGroupWithPortionsByProductIds_Returns_Correctly() {

        getOrderDetailFinalProductPortionDTOMap.put(1L, getOrderDetailFinalProductPortionDTOS);
        getOrderDetailFinalProductGroupDTOMap.put(1L, getOrderDetailFinalProductGroupDTO);

        when(mockGetOrderDetailFinalProductPortionByGroupIdUseCase
                .invoke(orderFinalProductIds))
                .thenReturn(getOrderDetailFinalProductPortionDTOMap);

        when(mockGetOrderRemovedPortionsByProductIdUseCase.invoke(orderFinalProductIds))
                .thenReturn(new ArrayList<>());

        final Map<Long, List<GetOrderDetailFinalProductGroupDTO>> result = getOrderDetailGroupWithPortionsByProductIdsUseCase
                .invoke(orderFinalProductIds);

        assertEquals(getOrderDetailFinalProductGroupDTOMap, result);
    }
}
