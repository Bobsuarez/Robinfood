package com.robinfood.core.dtos.response.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class ResponseOrderDiscountDTO {

    private final Long externalDiscount;

    private final Long finalProductId;

    private final Long id;

    private final Long typeId;

    private final BigDecimal value;
}
