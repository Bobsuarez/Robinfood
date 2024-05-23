package com.robinfood.orderorlocalserver.mappers;

import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDetailProductTaxDTO;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDetailProductTaxEntity;

public final class OrderDetailProductTaxMappers {

    private OrderDetailProductTaxMappers() {
        // this constructor is empty because it is a mapper class
    }

    public static OrderDetailProductTaxDTO toOrderDetailProductTaxMappers(
            OrderDetailProductTaxEntity orderDetailProductTaxEntity
    ) {
        return new OrderDetailProductTaxDTO(
                orderDetailProductTaxEntity.getFamilyTypeId(),
                orderDetailProductTaxEntity.getId(),
                orderDetailProductTaxEntity.getPrice(),
                orderDetailProductTaxEntity.getTaxTypeId(),
                orderDetailProductTaxEntity.getTaxTypeName(),
                orderDetailProductTaxEntity.getValue()
        );
    }
}
