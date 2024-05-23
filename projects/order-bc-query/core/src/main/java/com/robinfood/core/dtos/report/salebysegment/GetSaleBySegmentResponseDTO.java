package com.robinfood.core.dtos.report.salebysegment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class GetSaleBySegmentResponseDTO {

    @JsonProperty("companies")
    public List<CompanyBySegmentDTO> companiesBySegmentDTOList;

}
