package com.robinfood.app.usecases.createfoodcoins;


import com.robinfood.app.datamocks.dto.input.RequestOrderTransactionDTOMock;
import com.robinfood.core.dtos.request.transaction.RequestOrderTransactionDTO;
import com.robinfood.core.entities.OrderFoodCoinEntity;
import com.robinfood.repository.orderfoodcoins.IOrderFoodCoinRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateOrderFoodCoinsUseCaseTest {
    @InjectMocks
    private CreateOrderFoodCoinsUseCase createOrderFoodCoinsUseCase;

    @Mock
    private IOrderFoodCoinRepository orderFoodCoinRepository;

    RequestOrderTransactionDTO requestnull = RequestOrderTransactionDTOMock.requestOrderTransactionDTOFoodCoins();

    RequestOrderTransactionDTO request = RequestOrderTransactionDTOMock.inputOrderTransactionValidatedDTO();

    @Test
    void save_order_food_coins_success() {

        Boolean result = createOrderFoodCoinsUseCase.invoke(requestnull, List.of(1L)).join();
        Assertions.assertTrue(result);
    }
    @Test
    void save_order_food_coins_success_null() {

        OrderFoodCoinEntity orderFoodCoinEntity = new OrderFoodCoinEntity();
        orderFoodCoinEntity.setOrderId(1L);
        orderFoodCoinEntity.setValue(BigDecimal.ONE);
        when(orderFoodCoinRepository.save(orderFoodCoinEntity))
                .thenReturn(orderFoodCoinEntity);
        Boolean result = createOrderFoodCoinsUseCase.invoke(request, List.of(1L)).join();
        Assertions.assertTrue(result);
    }


}
