package com.robinfood.app.usecases.getorderremovedportionsbyproductid;

import com.robinfood.core.dtos.OrderDetailRemovedPortionDTO;
import com.robinfood.core.entities.FinalProductRemovedPortionEntity;
import com.robinfood.repository.orderremovedportions.IOrderRemovedPortionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetOrderRemovedPortionsByProductIdUseCaseTest {

    @Mock
    private IOrderRemovedPortionRepository mockOrderRemovedPortionRepository;

    @InjectMocks
    private GetOrderRemovedPortionsByProductIdUseCase getOrderRemovedPortionsByProductIdUseCase;

    private final List<Long> finalProductIds = Arrays.asList(1L, 2L);
    private final List<FinalProductRemovedPortionEntity> finalProductRemovedPortionEntities = Arrays.asList(
            new FinalProductRemovedPortionEntity(
                    LocalDateTime.now(),
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    1L,
                    "Rice"
            ),
            new FinalProductRemovedPortionEntity(
                    LocalDateTime.now(),
                    2L,
                    2L,
                    2L,
                    1L,
                    2L,
                    2L,
                    "Beans"
            )
    );

    private List<OrderDetailRemovedPortionDTO> orderDetailRemovedPortionDTOS = Arrays.asList(
            new OrderDetailRemovedPortionDTO(
                    1L,
                    1L,
                    "Rice",
                    1L
            ),
            new OrderDetailRemovedPortionDTO(
                    2L,
                    2L,
                    "Beans",
                    2L
            )
    );

    @Test
    void test_Get_Removed_Portions_By_FinalProductIds_ReturnsCorrectly() {
        when(mockOrderRemovedPortionRepository.findAllByOrderFinalProductIdIn(finalProductIds))
                .thenReturn(finalProductRemovedPortionEntities);

        final List<OrderDetailRemovedPortionDTO> result = getOrderRemovedPortionsByProductIdUseCase.invoke(finalProductIds);

        assertEquals(orderDetailRemovedPortionDTOS, result);
    }
}
