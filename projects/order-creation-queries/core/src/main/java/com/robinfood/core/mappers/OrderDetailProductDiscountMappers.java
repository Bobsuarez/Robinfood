package com.robinfood.core.mappers;

import com.robinfood.core.dtos.OrderDetailProductDiscountDTO;
import com.robinfood.core.entities.OrderDetailProductDiscountEntity;

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
