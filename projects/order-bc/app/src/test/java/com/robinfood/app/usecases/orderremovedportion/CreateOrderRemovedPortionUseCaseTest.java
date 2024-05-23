package com.robinfood.app.usecases.orderremovedportion;

import com.robinfood.app.usecases.createorderremovedportion.CreateOrderRemovedPortionUseCase;
import com.robinfood.core.dtos.request.order.FinalProductRemovedPortionDTO;
import com.robinfood.core.entities.FinalProductRemovedPortionEntity;
import com.robinfood.repository.orderremovedportions.IOrderRemovedPortionRepository;
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
public class CreateOrderRemovedPortionUseCaseTest {

    @Mock
    private IOrderRemovedPortionRepository orderRemovedPortionRepository;

    @InjectMocks
    private CreateOrderRemovedPortionUseCase createOrderRemovedPortionUseCase;

    private FinalProductRemovedPortionEntity finalProductRemovedPortionEntity = new FinalProductRemovedPortionEntity(
            null,
            1L,
            1L,
            null,
            1L,
            1l,
            1L,
            "Maíz"
    );

    private FinalProductRemovedPortionDTO removedPortionDTO = new FinalProductRemovedPortionDTO(
            1L,
            1L,
            "Maíz"
    );

    @Test
    void test_CreateOrderRemovedPortion_When_Save_Success() {
        List<FinalProductRemovedPortionEntity> orderRemovedPortionEntities = new ArrayList<>();
        List<FinalProductRemovedPortionEntity> orderRemovedPortionEntitiesSaved = new ArrayList<>();


        List<FinalProductRemovedPortionDTO> removedPortionsDTO = new ArrayList<>();

        removedPortionDTO.setFinalProductId(1L);
        removedPortionDTO.setOrderFinalProductId(1L);
        removedPortionDTO.setOrderId(1L);

        orderRemovedPortionEntities.add(finalProductRemovedPortionEntity);

        removedPortionsDTO.add(removedPortionDTO);

        orderRemovedPortionEntitiesSaved.add(new FinalProductRemovedPortionEntity(
                finalProductRemovedPortionEntity.getCreatedAt(),
                finalProductRemovedPortionEntity.getFinalProductId(),
                finalProductRemovedPortionEntity.getGroupId(),
                1L,
                finalProductRemovedPortionEntity.getOrderId(),
                finalProductRemovedPortionEntity.getOrderFinalProductId(),
                finalProductRemovedPortionEntity.getPortionId(),
                finalProductRemovedPortionEntity.getPortionName()
        ));


        when(orderRemovedPortionRepository.saveAll(orderRemovedPortionEntities))
                .thenReturn(orderRemovedPortionEntitiesSaved);

        final Boolean result = createOrderRemovedPortionUseCase.invoke(removedPortionsDTO).join();

        Assertions.assertTrue(result);
    }
}
