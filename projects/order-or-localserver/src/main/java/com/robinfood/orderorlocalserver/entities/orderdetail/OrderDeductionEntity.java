package com.robinfood.orderorlocalserver.entities.orderdetail;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDeductionEntity {

    private final BigDecimal value;
}
