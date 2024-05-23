package com.robinfood.core.dtos.reportsalesstore;

import com.robinfood.core.dtos.salesstore.PercentageSalesDifferenceDTO;
import com.robinfood.core.dtos.salesstore.ValueSalesOrderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class ReportOrdersPaymentDTO {

    public PercentageSalesDifferenceDTO percentageSalesDifference;

    public ValueSalesOrderDTO salesAWeekBefore;

    public ValueSalesOrderDTO salesCurrent;
}
