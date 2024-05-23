package com.robinfood.core.dtos.reportordersalescompanies;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class TotalCompaniesOrderSalesDTO {

    private String currency;

    private  String name;

    private TotalOrderSalesDTO orders;
}
