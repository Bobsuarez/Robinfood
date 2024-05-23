package com.robinfood.core.dtos.reportordersalescompanies;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class OrderSalesDTO {

    private SalesAWeekBeforeDTO salesAWeekBefore;

    private SalesCurrentDTO salesCurrent;

    private PercentageSalesDifferenceDTO percentageSalesDifference;
}
