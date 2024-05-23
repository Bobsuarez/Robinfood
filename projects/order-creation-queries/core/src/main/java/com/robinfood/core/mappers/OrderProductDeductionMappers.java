package com.robinfood.core.mappers;

import com.robinfood.core.dtos.OrderProductDeductionDTO;
import com.robinfood.core.entities.OrderProductDeductionEntity;

public final class OrderProductDeductionMappers {

    private OrderProductDeductionMappers() {
        // this constructor is empty because it is a mapper class
    }

    public static OrderProductDeductionDTO toOrderProductDeductionDTO(
            OrderProductDeductionEntity orderProductDeductionEntity
    ) {
        return new OrderProductDeductionDTO(
                orderProductDeductionEntity.getValue()
        );
    }
}
