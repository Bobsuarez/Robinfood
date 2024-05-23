package com.robinfood.orderorlocalserver.mappers;

import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailDiscountDTO;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailDiscountEntity;

public final class OrderDetailDiscountMappers {

    private OrderDetailDiscountMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static OrderDetailDiscountDTO toOrderDetailDiscountDTO(
            OrderDetailDiscountEntity orderDetailDiscountEntity
    ) {
        return new OrderDetailDiscountDTO(
                orderDetailDiscountEntity.getId(),
                orderDetailDiscountEntity.getTypeId(),
                orderDetailDiscountEntity.getValue()
        );
    }
}
