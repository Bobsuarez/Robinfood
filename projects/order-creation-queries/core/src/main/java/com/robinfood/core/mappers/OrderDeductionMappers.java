package com.robinfood.core.mappers;

import com.robinfood.core.dtos.OrderDeductionDTO;
import com.robinfood.core.entities.OrderDeductionEntity;

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
