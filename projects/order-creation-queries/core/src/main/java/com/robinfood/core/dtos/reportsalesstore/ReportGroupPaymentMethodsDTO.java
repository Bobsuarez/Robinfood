package com.robinfood.core.dtos.reportsalesstore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class ReportGroupPaymentMethodsDTO {

    public List<ReportSalesPaymentMethodDTO> items;

    public ReportTotalOrdersPaymentDTO total;
}
