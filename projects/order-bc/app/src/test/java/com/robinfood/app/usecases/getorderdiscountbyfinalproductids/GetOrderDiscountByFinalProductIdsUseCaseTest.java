package com.robinfood.app.usecases.getorderdiscountbyfinalproductids;

import com.robinfood.core.dtos.OrderDiscountDTO;
import com.robinfood.core.entities.OrderDiscountEntity;
import com.robinfood.repository.orderdiscount.IOrderDiscountCRUDRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetOrderDiscountByFinalProductIdsUseCaseTest {

    @Mock
    private IOrderDiscountCRUDRepository orderDiscountCRUDRepository ;

    @InjectMocks
    private GetOrderDiscountByFinalProductIdsUseCase getOrderDiscountByFinalProductIdsUseCase;

    private final List<Long> finalProductIds = new ArrayList<>(Arrays.asList(1L, 2L, 3L));

    private final List<OrderDiscountEntity> orderDiscountEntities = new ArrayList<>(Arrays.asList(
            new OrderDiscountEntity(
                    null,
                    1L,
                    5000.0,
                    1L,
                    1L,
                    null,
                    1L,
                    null
            ),
            new OrderDiscountEntity(
                    null,
                    2L,
                    1000.0,
                    2L,
                    1L,
                    null,
                    2L,
                    null
            )
    ));

    private final List<OrderDiscountDTO> orderDiscountDTOS = new ArrayList<>(Arrays.asList(
            new OrderDiscountDTO(
                    1L,
                    5000.0,
                    1L,
                    1L,
                    null,
                    1L
            ),
            new OrderDiscountDTO(
                    2L,
                    1000.0,
                    2L,
                    1L,
                    null,
                    2L
            )
    ));

    @Test
    void test_GetOrderDiscountsByProductIds_Returns_Correctly() {

        when(orderDiscountCRUDRepository.findOrderDiscountEntitiesByOrderFinalProductIdIn(finalProductIds))
                .thenReturn(orderDiscountEntities);

        final List<OrderDiscountDTO> result = getOrderDiscountByFinalProductIdsUseCase.invoke(finalProductIds);

        assertEquals(orderDiscountDTOS, result);
    }
}
