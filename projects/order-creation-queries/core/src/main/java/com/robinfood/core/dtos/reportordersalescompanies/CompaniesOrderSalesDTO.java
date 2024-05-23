package com.robinfood.core.dtos.reportordersalescompanies;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class CompaniesOrderSalesDTO {

    private CountryDTO country;

    private String currency;

    private Long id;

    private  String name;

    private OrderSalesDTO orders;
}
