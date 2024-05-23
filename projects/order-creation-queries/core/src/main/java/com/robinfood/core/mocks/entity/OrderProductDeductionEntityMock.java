package com.robinfood.core.mocks.entity;

import com.robinfood.core.entities.OrderProductDeductionEntity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class OrderProductDeductionEntityMock {

    public static OrderProductDeductionEntity getDataDefault() {
        return new OrderProductDeductionEntity(
                BigDecimal.valueOf(10.0)
        );
    }

    public static List<OrderProductDeductionEntity> list() {
        return Collections.singletonList(
                getDataDefault()
        );
    }
}
