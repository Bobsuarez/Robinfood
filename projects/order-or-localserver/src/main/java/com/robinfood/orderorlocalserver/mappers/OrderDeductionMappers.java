package com.robinfood.orderorlocalserver.mappers;

import com.robinfood.orderorlocalserver.dtos.orderdetail.OrderDeductionDTO;
import com.robinfood.orderorlocalserver.entities.orderdetail.OrderDeductionEntity;

public final class OrderDeductionMappers {

    private OrderDeductionMappers() {
        // this constructor is empty because it is a mapper class
    }

    public static OrderDeductionDTO toOrderDeductionDTO(
            OrderDeductionEntity orderDeductionEntity
    ) {
        return new OrderDeductionDTO(
                orderDeductionEntity.getValue()
        );
    }
}
