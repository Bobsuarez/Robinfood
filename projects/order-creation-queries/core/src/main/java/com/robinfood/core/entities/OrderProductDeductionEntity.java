package com.robinfood.core.entities;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderProductDeductionEntity {

    private final BigDecimal value;
}
