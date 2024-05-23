package com.robinfood.core.dtos.reportsalesstore;

import com.robinfood.core.dtos.salesstore.OrdersPaymentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class ReportSalesPaymentMethodDTO {

    public Long id;

    public String name;

    public ReportOrdersPaymentDTO orders;
}
