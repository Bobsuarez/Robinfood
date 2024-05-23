package com.robinfood.app.usecases.createorderchangedportion;

import com.robinfood.core.dtos.OrderChangedPortionDTO;
import com.robinfood.core.dtos.PortionProductDTO;
import com.robinfood.core.dtos.request.order.*;
import com.robinfood.core.entities.OrderChangedPortionEntity;
import com.robinfood.repository.orderchangedportions.IOrderChangedPortionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateOrderChangedPortionUseCaseTest {

    @Mock
    private IOrderChangedPortionRepository orderChangedPortionRepository;

    @InjectMocks
    private CreateOrderChangedPortionUseCase createOrderChangedPortionUseCase;

    private final OrderChangedPortionEntity orderChangedPortionEntity = new OrderChangedPortionEntity(
            1L,
            "Carne Molida",
            1L,
            "Carne Molida procesada",
            null,
            null,
            1L,
            1L,
            1L,
            "Carne desmechada",
            3L,
            "Carne desmechada procesada"
            );

    @Test
    void test_CreateOrderChangedPortion_When_Save_Success() {
        List<OrderChangedPortionEntity> orderChangedPortionEntities = new ArrayList<>();
        orderChangedPortionEntities.add(orderChangedPortionEntity);

        List<FinalProductPortionDTO> portionsReplacedDTO = new ArrayList<>();

        FinalProductPortionDTO portionReplacedDTO = new FinalProductPortionDTO(
                false,
                1L,
                0.0,
                0,
                1,
                new PortionGroupDTO(
                        1L,
                        "Ingredientes",
                        "sku"
                ),
                1L,
                "Carne desmechada",
                3L,
                1L,
                1L,
                100.0,
                new RequestOrderPortionProductDTO(
                        3L,
                        "Carne desmechada procesada"
                ),
                1,
                new ReplacementPortionDTO(
                        1L,
                        "Carne Molida",
                        new com.robinfood.core.dtos.PortionProductDTO(
                                "Carne Molida procesada",
                                1L
                        ),
                        "",
                        1L,
                        0.0,
                        new OriginalReplacementPortionDTO(
                                1L,
                                "Carne desmechada",
                                new PortionProductDTO(
                                        "Carne desmechada procesada",
                                        3L
                                ),
                                "sku",
                                1L,
                                1.0
                        )
                ),
                "sku",
                1L,
                1L,
                1.0
        );

        portionReplacedDTO.setCompanyId(1L);
        portionReplacedDTO.setOrderId(1L);
        portionReplacedDTO.setOrderFinalProductPortionId(1L);
        portionReplacedDTO.setOrderFinalProductId(1L);

        portionsReplacedDTO.add(portionReplacedDTO);

        List<OrderChangedPortionEntity> orderChangedPortionEntityList = new ArrayList<>();

        orderChangedPortionEntityList.add(new OrderChangedPortionEntity(
                orderChangedPortionEntity.getChangedPortionId(),
                orderChangedPortionEntity.getChangedPortionName(),
                orderChangedPortionEntity.getChangedProductId(),
                orderChangedPortionEntity.getChangedProductName(),
                orderChangedPortionEntity.getCreatedAt(),
                1L,
                orderChangedPortionEntity.getOrderId(),
                orderChangedPortionEntity.getOrderFinalProductPortionId(),
                orderChangedPortionEntity.getOriginalPortionId(),
                orderChangedPortionEntity.getOriginalPortionName(),
                orderChangedPortionEntity.getOriginalProductId(),
                orderChangedPortionEntity.getChangedProductName()
        ));

        when(orderChangedPortionRepository.saveAll(orderChangedPortionEntities))
                .thenReturn(orderChangedPortionEntityList);

        List<OrderChangedPortionDTO> result = createOrderChangedPortionUseCase.invoke(portionsReplacedDTO);

        Assertions.assertEquals(result.size(), 1);
        Assertions.assertEquals(result.get(0).getId(), 1L);
    }
}
