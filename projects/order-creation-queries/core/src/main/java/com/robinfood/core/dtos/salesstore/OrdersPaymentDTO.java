package com.robinfood.core.dtos.salesstore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrdersPaymentDTO {

    ValueSalesOrderDTO salesAWeekBefore;

    ValueSalesOrderDTO salesCurrent;
}
