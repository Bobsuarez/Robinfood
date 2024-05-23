package com.robinfood.app.usecases.orderfinalproductportion;

import com.robinfood.app.usecases.createorderchangedportion.ICreateOrderChangedPortionUseCase;
import com.robinfood.app.usecases.createorderfinalproductportion.CreateOrderFinalProductPortionsUseCase;
import com.robinfood.core.dtos.request.order.FinalProductPortionDTO;
import com.robinfood.core.dtos.request.order.PortionGroupDTO;
import com.robinfood.core.dtos.request.order.RequestOrderPortionProductDTO;
import com.robinfood.core.dtos.request.order.ReplacementPortionDTO;
import com.robinfood.core.entities.OrderFinalProductPortionEntity;
import com.robinfood.repository.orderfinalproductportions.IOrderFinalProductPortionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateOrderFinalProductPortionUseCaseTestUseCaseTest {

    @Mock
    private IOrderFinalProductPortionRepository orderFinalProductPortionRepository;

    @Mock
    private ICreateOrderChangedPortionUseCase createOrderChangedPortionUseCase;

    @InjectMocks
    private CreateOrderFinalProductPortionsUseCase createOrderFinalProductPortionUseCase;

    private final OrderFinalProductPortionEntity orderFinalProductPortionEntityByFree = new OrderFinalProductPortionEntity(
            false,
            100.0,
            1L,
            true,
            null,
            0.0,
            1L,
            0,
            1L,
            "Ingredientes",
            "sku",
            0L,
            new Date(System.currentTimeMillis()).toString(),
            1L,
            1L,
            1L,
            "Carne desmechada",
            "sku",
            0,
            3L,
            "Carne desmechada procesada",
            1,
            1,
            1L,
            1.0,
            null
    );

    private final OrderFinalProductPortionEntity orderFinalProductPortionEntityByAddition = new OrderFinalProductPortionEntity(
            true,
            100.0,
            1L,
            false,
            null,
            0.0,
            1L,
            0,
            1L,
            "Ingredientes",
            "sku",
            0L,
            new Date(System.currentTimeMillis()).toString(),
            1L,
            1L,
            1L,
            "Carne desmechada",
            "sku",
            0,
            3L,
            "Carne desmechada procesada",
            4,
            0,
            1L,
            1.0,
            null
    );

    @Test
    void test_CreateOrderFinalProductPortion_When_Save_Success() {
        final List<OrderFinalProductPortionEntity> orderFinalProductPortionEntities = new ArrayList<>();
        final List<OrderFinalProductPortionEntity> orderFinalProductPortionEntitiesSaved = new ArrayList<>();
        final List<FinalProductPortionDTO> portionsDTO = new ArrayList<>();

        orderFinalProductPortionEntities.add(orderFinalProductPortionEntityByFree);
        orderFinalProductPortionEntities.add(orderFinalProductPortionEntityByAddition);

        orderFinalProductPortionEntitiesSaved.add(
                new OrderFinalProductPortionEntity(
                        orderFinalProductPortionEntityByFree.getAddition(),
                        orderFinalProductPortionEntityByFree.getBasePrice(),
                        orderFinalProductPortionEntityByFree.getCompanyId(),
                        orderFinalProductPortionEntityByFree.getChangedProduct(),
                        orderFinalProductPortionEntityByFree.getCreatedAt(),
                        orderFinalProductPortionEntityByFree.getDiscount(),
                        orderFinalProductPortionEntityByFree.getDicUnitId(),
                        orderFinalProductPortionEntityByFree.getEffectiveSale(),
                        orderFinalProductPortionEntityByFree.getGroupId(),
                        orderFinalProductPortionEntityByFree.getGroupName(),
                        orderFinalProductPortionEntityByFree.getGroupSku(),
                        1L,
                        orderFinalProductPortionEntityByFree.getOperationDate(),
                        orderFinalProductPortionEntityByFree.getOrderId(),
                        orderFinalProductPortionEntityByFree.getOrderFinalProductId(),
                        orderFinalProductPortionEntityByFree.getPortionId(),
                        orderFinalProductPortionEntityByFree.getPortionName(),
                        orderFinalProductPortionEntityByFree.getPortionSku(),
                        orderFinalProductPortionEntityByFree.getPortionStatus(),
                        orderFinalProductPortionEntityByFree.getProductId(),
                        orderFinalProductPortionEntityByFree.getProductName(),
                        orderFinalProductPortionEntityByFree.getQuantity(),
                        orderFinalProductPortionEntityByFree.getQuantityFree(),
                        orderFinalProductPortionEntityByFree.getStoreId(),
                        orderFinalProductPortionEntityByFree.getUnitsNumber(),
                        orderFinalProductPortionEntityByFree.getUpdatedAt()
                )
        );

        orderFinalProductPortionEntitiesSaved.add(
                new OrderFinalProductPortionEntity(
                        orderFinalProductPortionEntityByAddition.getAddition(),
                        orderFinalProductPortionEntityByAddition.getBasePrice(),
                        orderFinalProductPortionEntityByAddition.getCompanyId(),
                        orderFinalProductPortionEntityByAddition.getChangedProduct(),
                        orderFinalProductPortionEntityByAddition.getCreatedAt(),
                        orderFinalProductPortionEntityByAddition.getDiscount(),
                        orderFinalProductPortionEntityByAddition.getDicUnitId(),
                        orderFinalProductPortionEntityByAddition.getEffectiveSale(),
                        orderFinalProductPortionEntityByAddition.getGroupId(),
                        orderFinalProductPortionEntityByAddition.getGroupName(),
                        orderFinalProductPortionEntityByAddition.getGroupSku(),
                        1L,
                        orderFinalProductPortionEntityByAddition.getOperationDate(),
                        orderFinalProductPortionEntityByAddition.getOrderId(),
                        orderFinalProductPortionEntityByAddition.getOrderFinalProductId(),
                        orderFinalProductPortionEntityByAddition.getPortionId(),
                        orderFinalProductPortionEntityByAddition.getPortionName(),
                        orderFinalProductPortionEntityByAddition.getPortionSku(),
                        orderFinalProductPortionEntityByAddition.getPortionStatus(),
                        orderFinalProductPortionEntityByAddition.getProductId(),
                        orderFinalProductPortionEntityByAddition.getProductName(),
                        orderFinalProductPortionEntityByAddition.getQuantity(),
                        orderFinalProductPortionEntityByAddition.getQuantityFree(),
                        orderFinalProductPortionEntityByAddition.getStoreId(),
                        orderFinalProductPortionEntityByAddition.getUnitsNumber(),
                        orderFinalProductPortionEntityByAddition.getUpdatedAt()
                )
        );

        FinalProductPortionDTO portionDTO = new FinalProductPortionDTO(
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
                1L,
                3L,
                1L,
                100.00,
                new RequestOrderPortionProductDTO(
                        3L,
                        "Carne desmechada procesada"
                ),
                5,
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
                        null
                ),
                "sku",
                1L,
                1L,
                1.0
        );


        portionDTO.setCompanyId(1L);
        portionDTO.setOrderId(1L);
        portionDTO.setOrderFinalProductPortionId(1L);
        portionDTO.setOrderFinalProductId(1L);

        portionsDTO.add(portionDTO);

        when(orderFinalProductPortionRepository.saveAll(orderFinalProductPortionEntities))
                .thenReturn(orderFinalProductPortionEntitiesSaved);

        Boolean result = createOrderFinalProductPortionUseCase.invoke(
                portionsDTO
        ).join();

        Assertions.assertTrue(result);
    }

}
