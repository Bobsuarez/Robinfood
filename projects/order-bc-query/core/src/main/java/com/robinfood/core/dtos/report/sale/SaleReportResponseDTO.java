package com.robinfood.core.dtos.report.sale;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class SaleReportResponseDTO {

    @JsonProperty("companies")
    public List<CompanySaleDTO> companyList;

}
