package com.robinfood.orderorlocalserver.dtos.orderdetail;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDeductionDTO {

    private final BigDecimal value;
}
