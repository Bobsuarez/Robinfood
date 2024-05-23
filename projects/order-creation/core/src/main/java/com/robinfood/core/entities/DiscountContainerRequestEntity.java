package com.robinfood.core.entities;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class DiscountContainerRequestEntity {

    private final BigDecimal discount;

    private final String entity;

    private final Long id;

    private final Integer quantity;

    private final BigDecimal value;
}
