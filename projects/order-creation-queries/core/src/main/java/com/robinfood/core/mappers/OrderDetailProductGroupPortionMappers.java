package com.robinfood.core.mappers;

import com.robinfood.core.dtos.OrderDetailProductGroupPortionDTO;
import com.robinfood.core.entities.OrderDetailProductGroupPortionEntity;

public final class OrderDetailProductGroupPortionMappers {

    private OrderDetailProductGroupPortionMappers() {
        // this constructor is empty because it is a mapper class
    }

    public static OrderDetailProductGroupPortionDTO toOrderDetailProductGroupPortionDTO(
            OrderDetailProductGroupPortionEntity orderDetailProductGroupPortionEntity
    ) {
        return new OrderDetailProductGroupPortionDTO(
                orderDetailProductGroupPortionEntity.getAddition(),
                OrderDetailChangedPortionMappers.toOrderDetailChangedPortionDTO(
                        orderDetailProductGroupPortionEntity.getChangedPortion()),
                orderDetailProductGroupPortionEntity.getDiscount(),
                orderDetailProductGroupPortionEntity.getId(),
                orderDetailProductGroupPortionEntity.getName(),
                orderDetailProductGroupPortionEntity.getProductId(),
                orderDetailProductGroupPortionEntity.getPrice(),
                orderDetailProductGroupPortionEntity.getQuantity(),
                orderDetailProductGroupPortionEntity.getFree(),
                orderDetailProductGroupPortionEntity.getSku(),
                orderDetailProductGroupPortionEntity.getUnits(),
                orderDetailProductGroupPortionEntity.getWeight()
        );
    }
}
