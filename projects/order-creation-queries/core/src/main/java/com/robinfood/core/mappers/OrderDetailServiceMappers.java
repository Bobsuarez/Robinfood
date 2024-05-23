package com.robinfood.core.mappers;

import com.robinfood.core.dtos.OrderDetailServiceDTO;
import com.robinfood.core.entities.OrderDetailServiceEntity;

public final class OrderDetailServiceMappers {

    private OrderDetailServiceMappers(){
        // this constructor is empty because it is a mapper class
    }

    public static OrderDetailServiceDTO toOrderDetailServiceDTO (
            OrderDetailServiceEntity orderDetailServiceEntity
    ){

        return OrderDetailServiceDTO.builder()
                .discount(orderDetailServiceEntity.getDiscount())
                .id(orderDetailServiceEntity.getId())
                .name(orderDetailServiceEntity.getName())
                .quantity(orderDetailServiceEntity.getQuantity())
                .subTotal(orderDetailServiceEntity.getSubTotal())
                .tax(orderDetailServiceEntity.getTax())
                .taxPercentage(orderDetailServiceEntity.getTaxPercentage())
                .unitPrice(orderDetailServiceEntity.getUnitPrice())
                .build();
    }
}
