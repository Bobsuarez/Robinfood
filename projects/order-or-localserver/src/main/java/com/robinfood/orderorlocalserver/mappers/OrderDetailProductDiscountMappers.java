package com.robinfood.orderorlocalserver.mappers;

import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailProductDiscountDTO;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailProductDiscountEntity;

public final class OrderDetailProductDiscountMappers {

    private OrderDetailProductDiscountMappers() {
        // this constructor is empty because it is a mapper class
    }

    public static OrderDetailProductDiscountDTO toOrderDetailProductDiscountDTO(
            OrderDetailProductDiscountEntity orderDetailProductDiscountEntity
    ) {
        return new OrderDetailProductDiscountDTO(
                orderDetailProductDiscountEntity.getId(),
                orderDetailProductDiscountEntity.getTypeId(),
                orderDetailProductDiscountEntity.getValue()
        );
    }
}
