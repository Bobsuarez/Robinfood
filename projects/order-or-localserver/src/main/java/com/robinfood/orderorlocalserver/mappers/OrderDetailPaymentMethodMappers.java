package com.robinfood.orderorlocalserver.mappers;

import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailPaymentMethodDTO;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailPaymentMethodEntity;

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
