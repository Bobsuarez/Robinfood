package com.robinfood.core.entities;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class DiscountContainerResponseEntity {

    private final String entity;

    private final Integer quantity;

    private final String reason;

    private final Integer sourceId;

    private final Boolean valid;

    private final BigDecimal value;

}
