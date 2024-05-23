package com.robinfood.core.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class DiscountContainerRequestDTO implements Serializable {

    private final BigDecimal discount;

    private final Long id;

    private final String name;

    private final Integer quantity;

    private final BigDecimal productValue;
}
