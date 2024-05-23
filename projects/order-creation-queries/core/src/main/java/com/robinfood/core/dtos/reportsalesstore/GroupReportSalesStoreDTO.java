package com.robinfood.core.dtos.reportsalesstore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class GroupReportSalesStoreDTO {

    public Long id;

    public String name;

    public ReportGroupPaymentMethodsDTO paymentMethods;
}
