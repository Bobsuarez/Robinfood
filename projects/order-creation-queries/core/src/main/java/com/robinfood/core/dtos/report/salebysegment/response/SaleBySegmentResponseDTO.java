package com.robinfood.core.dtos.report.salebysegment.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class SaleBySegmentResponseDTO {

    private final List<CompanyDTOResponse> companies;

}
