package com.robinfood.app.usecases.changeorderfinalproductportions;

import com.robinfood.app.datamocks.entity.OrderFinalProductPortionEntityMock;
import com.robinfood.core.entities.OrderFinalProductPortionEntity;
import com.robinfood.repository.orderfinalproductportions.IOrderFinalProductPortionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeOrderFinalProductPortionsUseCaseTest {

    @Mock
    private IOrderFinalProductPortionRepository orderFinalProductPortionRepository;

    @InjectMocks
    private ChangeOrderFinalProductPortionsUseCase changeOrderFinalProductPortionsUseCase;

    private final Long orderId = 1234L;

    @Test
    void test_ChangeOrderFinalProductPortions_When_State_is_Paid() {

        List<OrderFinalProductPortionEntity> portions = new OrderFinalProductPortionEntityMock().getDataDefaultList();

        String status ="ORDER_APPROVED_PAYMENT";

        when(orderFinalProductPortionRepository.findOrderFinalProductPortionEntityByOrderId(anyLong()))
            .thenReturn(portions);

        assertAll(()-> changeOrderFinalProductPortionsUseCase.invoke(orderId, status));

    }

    @Test
    void test_ChangeOrderFinalProductPortions_When_State_is_Cancel() {

        List<OrderFinalProductPortionEntity> portions = new OrderFinalProductPortionEntityMock().getDataDefaultList();

        portions.get(0).setEffectiveSale(1);

        String status ="ORDER_CANCEL";

        when(orderFinalProductPortionRepository.findOrderFinalProductPortionEntityByOrderId(anyLong()))
            .thenReturn(portions);

        assertAll(()-> changeOrderFinalProductPortionsUseCase.invoke(orderId, status));

    }
    @Test
    void test_ChangeOrderFinalProductPortions_When_Is_Other_State() {

        List<OrderFinalProductPortionEntity> portions = new OrderFinalProductPortionEntityMock().getDataDefaultList();

        portions.get(0).setEffectiveSale(1);

        String status ="OTHER_STATE";

        assertAll(()-> changeOrderFinalProductPortionsUseCase.invoke(orderId, status));

    }

    @Test
    void test_ChangeOrderFinalProductPortions_When_Is_Order_Dont_Portions() {

       List<OrderFinalProductPortionEntity> portions = new ArrayList<>();

        String status ="ORDER_APPROVED_PAYMENT";

        when(orderFinalProductPortionRepository.findOrderFinalProductPortionEntityByOrderId(anyLong()))
            .thenReturn(portions);

        assertAll(()-> changeOrderFinalProductPortionsUseCase.invoke(orderId, status));
    }

}
