package com.robinfood.repository.orderdiscount;

import com.robinfood.core.entities.OrderDiscountEntity;
import com.robinfood.repository.mocks.OrderDiscountEntityMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderDiscountRepositoryTest {

    @Mock
    private IOrderDiscountLocalDatasource mockOrderDiscountLocalDatasource;

    @InjectMocks
    private OrderDiscountRepository orderDiscountRepository;

    private final OrderDiscountEntityMocks orderDiscountEntityMocks = new OrderDiscountEntityMocks();

    @Test
    void test_Set_Local_Order_Discounts() {
        doNothing().when(mockOrderDiscountLocalDatasource)
                .setLocalOrderDiscountsEntities(Collections.singletonList(
                        orderDiscountEntityMocks.orderDiscountEntity
                ));

        orderDiscountRepository.setLocalOrderDiscounts(Collections.singletonList(
                orderDiscountEntityMocks.orderDiscountEntity
        ));

        verify(mockOrderDiscountLocalDatasource)
                .setLocalOrderDiscountsEntities(Collections.singletonList(
                        orderDiscountEntityMocks.orderDiscountEntity
                ));
    }

    @Test
    void test_Get_Local_Order_Discounts() {
        when(mockOrderDiscountLocalDatasource.getCurrentOrderDiscountsStored()).thenReturn(
                Collections.singletonList(
                        orderDiscountEntityMocks.orderDiscountEntity
                )
        );

        final List<OrderDiscountEntity> result = orderDiscountRepository.getLocalOrderDiscounts();

        verify(mockOrderDiscountLocalDatasource).getCurrentOrderDiscountsStored();
        assertEquals(Collections.singletonList(
                orderDiscountEntityMocks.orderDiscountEntity
        ), result);
    }
}
