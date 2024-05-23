package com.robinfood.core.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDeductionDTO {

    private final BigDecimal value;
}
