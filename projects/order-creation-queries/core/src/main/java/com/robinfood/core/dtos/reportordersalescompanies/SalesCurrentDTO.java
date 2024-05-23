package com.robinfood.core.dtos.reportordersalescompanies;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class SalesCurrentDTO {

    private CurrencyDTO currency;

    private BigDecimal value;
}
