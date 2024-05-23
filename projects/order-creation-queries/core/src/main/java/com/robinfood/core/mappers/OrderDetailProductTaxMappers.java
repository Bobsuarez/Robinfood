package com.robinfood.core.mappers;

import com.robinfood.core.dtos.OrderDetailProductTaxDTO;
import com.robinfood.core.entities.OrderDetailProductTaxEntity;

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
