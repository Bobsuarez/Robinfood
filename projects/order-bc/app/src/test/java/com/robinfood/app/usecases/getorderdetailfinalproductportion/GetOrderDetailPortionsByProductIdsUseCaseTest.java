package com.robinfood.app.usecases.getorderdetailfinalproductportion;

import com.robinfood.app.usecases.setchangedportions.ISetChangedPortionsUseCase;
import com.robinfood.core.dtos.DetailChangedPortionDTO;
import com.robinfood.core.dtos.GetOrderDetailFinalProductPortionDTO;
import com.robinfood.core.entities.OrderFinalProductPortionEntity;
import com.robinfood.repository.orderfinalproductportions.IOrderFinalProductPortionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetOrderDetailPortionsByProductIdsUseCaseTest {

    @Mock
    private ISetChangedPortionsUseCase mockSetChangedPortionsUseCase;

    @Mock
    private IOrderFinalProductPortionRepository mockOrderFinalProductPortionRepository;

    @InjectMocks
    private GetOrderDetailPortionsByProductIdsUseCase getOrderDetailPortionsByProductIdsUseCase;

    private final List<Long> orderFinalProductIds = new ArrayList<>(Arrays.asList(1L, 2L, 3L));

    private final List<OrderFinalProductPortionEntity> orderFinalProductPortionEntities = new ArrayList<>(
            Collections.singletonList(
                    new OrderFinalProductPortionEntity(
                            false,
                            8900.0,
                            1L,
                            false,
                            LocalDateTime.now(),
                            0.0,
                            1L,
                            0,
                            1L,
                            "ingredientes",
                            "sku",
                            1L,
                            "2021-06-01",
                            1L,
                            1L,
                            1L,
                            "arroz",
                            "sku",
                            1,
                            1L,
                            "arroz procesado",
                            1,
                            1,
                            1L,
                            1.0,
                            LocalDateTime.now()
                    )
            )
    );

    final Map<Long, List<GetOrderDetailFinalProductPortionDTO>> getOrderDetailFinalProductTaxDTOMap = new HashMap<>();

    final List<GetOrderDetailFinalProductPortionDTO> getOrderDetailFinalProductPortionsDTOS = new ArrayList<>(
            Collections.singletonList(
                    new GetOrderDetailFinalProductPortionDTO(
                            false,
                            null,
                            BigDecimal.ZERO,
                            1L,
                            1L,
                            "Arroz",
                            1L,
                            "ingredientes",
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
    private final DetailChangedPortionDTO suggestedPortionDTO = new DetailChangedPortionDTO(
            1L,
            "Carne desmechada",
            1L
    );

    @Test
    void test_GetOrderDetailPortionsByProductIds_Returns_Correctly() {

        getOrderDetailFinalProductTaxDTOMap.put(1L, getOrderDetailFinalProductPortionsDTOS);

        when(mockOrderFinalProductPortionRepository
                .findOrderFinalProductPortionEntityByOrderFinalProductIdIn(orderFinalProductIds))
                .thenReturn(orderFinalProductPortionEntities);

        when(mockSetChangedPortionsUseCase.invoke(orderFinalProductPortionEntities))
                .thenReturn(getOrderDetailFinalProductPortionsDTOS);

        final Map<Long, List<GetOrderDetailFinalProductPortionDTO>> result = getOrderDetailPortionsByProductIdsUseCase
                .invoke(orderFinalProductIds);
        getOrderDetailFinalProductPortionsDTOS.get(0).setChangedPortion(suggestedPortionDTO);
        assertEquals(getOrderDetailFinalProductTaxDTOMap, result);
    }
}
