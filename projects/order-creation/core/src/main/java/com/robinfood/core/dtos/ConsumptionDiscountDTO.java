package com.robinfood.core.dtos;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ConsumptionDiscountDTO  implements Serializable {

    private final Long id;

    private final String name;

    private final BigDecimal value;
}
