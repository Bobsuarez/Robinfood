package com.robinfood.app.usecases.getorderfoodcoins;

import com.robinfood.core.entities.OrderFoodCoinEntity;
import com.robinfood.repository.orderfoodcoins.IOrderFoodCoinRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

@Component
@Slf4j
public class GetOrderFoodCoinsUseCase implements  IGetOrderFoodCoinsUseCase{

    private final IOrderFoodCoinRepository foodCoinRepository;

    public GetOrderFoodCoinsUseCase(IOrderFoodCoinRepository foodCoinRepository) {
        this.foodCoinRepository = foodCoinRepository;
    }

    @Override
    public BigDecimal invoke(Long orderId) {

        log.info(
                "Start of the process that searches for the orders with orderId {}",
                orderId
        );

        OrderFoodCoinEntity orderFoodCoinEntity = foodCoinRepository.findByOrderId(orderId);
        if (Objects.nonNull(orderFoodCoinEntity)) {
            return orderFoodCoinEntity.getValue();
        }
        return BigDecimal.ZERO;
    }
}
