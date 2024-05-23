package com.robinfood.app.usecases.getorderdieductionbyfinalproductids;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.robinfood.core.dtos.OrderDeductionFinalProductDTO;
import com.robinfood.core.entities.OrderDeductionFinalProductEntity;
import com.robinfood.repository.orderproductodeduction.IOrderProductFinalDeductionRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetOrderDeductionsByFinalProductIdsUseCaseTest {

    @Mock
    private IOrderProductFinalDeductionRepository orderProductFinalDeductionRepository;

    @InjectMocks
    private GetOrderDeductionsByFinalProductIdsUseCase getOrderDeductionsByFinalProductIdsUseCase;

    private final List<Long> finalProductIds = new ArrayList<>(Arrays.asList(1L, 2L, 3L));

    private final List<OrderDeductionFinalProductEntity> orderDiscountEntities = List.of(
            OrderDeductionFinalProductEntity.builder()
                    .id(1L)
                    .orderId(1L)
                    .productFinalId(1L)
                    .orderId(1L)
                    .build()
            ,
            OrderDeductionFinalProductEntity.builder()
                    .id(2L)
                    .orderId(1L)
                    .productFinalId(2L)
                    .orderId(1L)
                    .build()
    );

    @Test
    void test_GetOrderDiscountsByProductIds_Returns_Correctly() {

        when(orderProductFinalDeductionRepository
                .findOrderDeductionFinalProductEntitiesByProductFinalIdIn(finalProductIds))
                .thenReturn(orderDiscountEntities);

        final List<OrderDeductionFinalProductDTO> result =
                getOrderDeductionsByFinalProductIdsUseCase.invoke(finalProductIds);

        assertNotNull(result);
        assertEquals(orderDiscountEntities.get(0).getOrderId(), result.get(0).getOrderId());
    }
}
