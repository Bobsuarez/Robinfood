package com.robinfood.app.usecases.getorderfoodcoins;

import com.robinfood.core.entities.OrderFoodCoinEntity;
import com.robinfood.repository.orderfoodcoins.IOrderFoodCoinRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class GetOrderFoodCoinsUseCaseTest {

    @InjectMocks
    private GetOrderFoodCoinsUseCase getOrderFoodCoinsUseCase;

    @Mock
    private IOrderFoodCoinRepository orderFoodCoinRepository;

    @Test
    void get_order_food_coins_success() {
        OrderFoodCoinEntity orderFoodCoinEntity = new OrderFoodCoinEntity();
        orderFoodCoinEntity.setOrderId(1L);
        orderFoodCoinEntity.setValue(BigDecimal.ONE);

        when(orderFoodCoinRepository.findByOrderId(anyLong()))
                .thenReturn(orderFoodCoinEntity);

        BigDecimal result = getOrderFoodCoinsUseCase.invoke(1L);

        assertEquals(result, BigDecimal.ONE);

    }

    @Test
    void get_order_food_coins_null() {
        OrderFoodCoinEntity orderFoodCoinEntity = new OrderFoodCoinEntity();
        orderFoodCoinEntity.setOrderId(1L);
        orderFoodCoinEntity.setValue(BigDecimal.ONE);

        when(orderFoodCoinRepository.findByOrderId(anyLong()))
                .thenReturn(null);

        BigDecimal result = getOrderFoodCoinsUseCase.invoke(1L);

        assertEquals(result, BigDecimal.ZERO);

    }

}
