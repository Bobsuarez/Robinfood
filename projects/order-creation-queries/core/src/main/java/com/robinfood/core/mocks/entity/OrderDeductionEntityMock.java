package com.robinfood.core.mocks.entity;

import com.robinfood.core.entities.OrderDeductionEntity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class OrderDeductionEntityMock {

    public static OrderDeductionEntity getDataDefault() {
        return new OrderDeductionEntity(
                BigDecimal.valueOf(10.0)
        );
    }

    public static List<OrderDeductionEntity> list() {
        return Collections.singletonList(
                getDataDefault()
        );
    }
}
