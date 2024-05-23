package com.robinfood.core.mappers.report.salesegment;

import com.robinfood.core.dtos.report.salebysegment.response.CompanyDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.SaleBySegmentResponseDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public final class SaleSegmentResponseMappers {

    private SaleSegmentResponseMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static SaleBySegmentResponseDTO saleSegmentToResponse(List<CompanyDTOResponse> listCompanyDTO) {

        return SaleBySegmentResponseDTO.builder()
                .companies(listCompanyDTO)
                .build();
    }
}
