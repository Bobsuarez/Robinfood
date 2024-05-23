package com.robinfood.app.usecases.setchangedportions;

import com.robinfood.core.dtos.DetailChangedPortionDTO;
import com.robinfood.core.dtos.GetOrderDetailFinalProductPortionDTO;
import com.robinfood.core.entities.OrderChangedPortionEntity;
import com.robinfood.core.entities.OrderFinalProductPortionEntity;
import com.robinfood.repository.orderchangedportions.IOrderChangedPortionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SetChangedPortionsUseCaseTest {

    @Mock
    private IOrderChangedPortionRepository mockOrderChangedPortionRepository;

    @InjectMocks
    private SetChangedPortionsUseCase setChangedPortionsUseCase;

    private final List<OrderFinalProductPortionEntity> orderFinalProductPortionEntities = new ArrayList<>(
            Arrays.asList(
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
                            "Arroz",
                            "sku",
                            1,
                            1L,
                            "arroz procesado",
                            1,
                            1,
                            1L,
                            1.0,
                            LocalDateTime.now()
                    ),
                    new OrderFinalProductPortionEntity(
                            false,
                            8900.0,
                            1L,
                            true,
                            LocalDateTime.now(),
                            0.0,
                            1L,
                            0,
                            1L,
                            "ingredientes",
                            "sku",
                            20236216L,
                            "2021-06-01",
                            1L,
                            2L,
                            1L,
                            "Arroz blanco",
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

    private final List<OrderChangedPortionEntity> orderChangedPortionEntities = Collections.singletonList(
            new OrderChangedPortionEntity(
                    28L,
                    "Arroz integral",
                    1L,
                    "89",
                    LocalDateTime.now(),
                    23596L,
                    926558L,
                    20236216L,
                    1L,
                    "Arroz blanco",
                    88L,
                    "Arroz blanco cocido"
            )
    );

    private final List<GetOrderDetailFinalProductPortionDTO> getOrderDetailFinalProductPortionDTOS = new ArrayList<>(
            Arrays.asList(
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
                    ),
                    new GetOrderDetailFinalProductPortionDTO(
                            false,
                            new DetailChangedPortionDTO(
                                    1L,
                                    "Arroz blanco",
                                    88L
                            ),
                            BigDecimal.ZERO,
                            2L,
                            1L,
                            "ingredientes",
                            1L,
                            "Arroz blanco",
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

    @Test
    void test_Changed_Portions_Are_Set_Correctly() {
        when(mockOrderChangedPortionRepository.findByOrderFinalProductPortionIdIn(Collections.singletonList(20236216L)))
                .thenReturn(orderChangedPortionEntities);

        final List<GetOrderDetailFinalProductPortionDTO> result = setChangedPortionsUseCase
                .invoke(orderFinalProductPortionEntities);

        assertEquals(getOrderDetailFinalProductPortionDTOS, result);
    }
}
