package com.robinfood.core.dtos.salesstore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class ValueSalesOrderDTO {

    public Integer counter;

    public BigDecimal value;
}
