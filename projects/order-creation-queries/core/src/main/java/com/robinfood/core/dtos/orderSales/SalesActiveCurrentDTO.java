package com.robinfood.core.dtos.orderSales;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class SalesActiveCurrentDTO {

    private BigDecimal value;
}
