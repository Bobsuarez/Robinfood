package com.robinfood.core.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class DiscountContainerResponseDTO implements Serializable  {

    private final String entity;

    private final Integer quantity;

    private final String reason;

    private final Integer sourceId;

    private final Boolean valid;

    private final BigDecimal value;
}
