package com.robinfood.app.mappers;

import com.robinfood.core.dtos.request.order.OrderDTO;
import com.robinfood.core.entities.OrderFoodCoinEntity;

public class OrderFoodCoinMapper {

    public static OrderFoodCoinEntity orderFoodCoinToOrderFoodCoinEntity(OrderDTO order, Long index) {

        OrderFoodCoinEntity orderFoodCoinEntity = new OrderFoodCoinEntity();
        orderFoodCoinEntity.setOrderId(index);
        orderFoodCoinEntity.setValue(order.getFoodcoins());

        return orderFoodCoinEntity;
    }

}
