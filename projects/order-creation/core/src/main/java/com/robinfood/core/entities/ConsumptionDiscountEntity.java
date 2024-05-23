package com.robinfood.core.entities;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ConsumptionDiscountEntity {

    private final Long id;

    private final String name;

    private final BigDecimal value;
}
