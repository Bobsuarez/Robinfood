package com.robinfood.core.mappers;

import com.robinfood.core.dtos.OrderDetailPaymentMethodDTO;
import com.robinfood.core.entities.OrderDetailPaymentMethodEntity;

public final class OrderDetailPaymentMethodMappers {

    private OrderDetailPaymentMethodMappers() {
        // this constructor is empty because it is a mapper class
    }

    public static OrderDetailPaymentMethodDTO toOrderDetailPaymentMethodDTO(
            OrderDetailPaymentMethodEntity orderDetailPaymentMethodEntity
    ) {
        return new OrderDetailPaymentMethodDTO(
                orderDetailPaymentMethodEntity.getDiscount(),
                orderDetailPaymentMethodEntity.getId(),
                orderDetailPaymentMethodEntity.getOriginId(),
                orderDetailPaymentMethodEntity.getSubtotal(),
                orderDetailPaymentMethodEntity.getTax(),
                orderDetailPaymentMethodEntity.getValue()
        );
    }
}
