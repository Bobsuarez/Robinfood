package com.robinfood.orderorlocalserver.mappers;

import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailProductGroupPortionDTO;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailProductGroupPortionEntity;

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
