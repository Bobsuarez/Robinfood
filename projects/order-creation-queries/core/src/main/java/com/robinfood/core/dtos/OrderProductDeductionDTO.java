package com.robinfood.core.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderProductDeductionDTO {

    private final BigDecimal value;
}
