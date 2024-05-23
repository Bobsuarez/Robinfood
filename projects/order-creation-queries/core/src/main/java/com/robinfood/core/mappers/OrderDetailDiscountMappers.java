package com.robinfood.core.mappers;

import com.robinfood.core.dtos.OrderDetailDiscountDTO;
import com.robinfood.core.entities.OrderDetailDiscountEntity;

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
