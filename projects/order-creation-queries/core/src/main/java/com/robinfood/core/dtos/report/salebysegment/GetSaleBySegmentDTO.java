package com.robinfood.core.dtos.report.salebysegment;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetSaleBySegmentDTO {

    private final List<CompanyBySegmentDTO> companies;

}
