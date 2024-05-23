package com.robinfood.core.dtos.orderSales;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class OrderActiveSalesDTO {

    private SalesActiveAWeekBeforeDTO salesAWeekBefore;

    private SalesActiveCurrentDTO salesCurrent;
}
