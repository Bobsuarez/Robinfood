package com.robinfood.core.dtos.reportordersalescompanies;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseOrderSalesDTO {

    private TotalCompaniesOrderSalesDTO totalCompanies;

    private List<CompaniesOrderSalesDTO> companies;
}
