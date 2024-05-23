package com.robinfood.app.datamocks.dto.output.sale;

import com.robinfood.core.dtos.report.sale.SaleReportResponseDTO;

import java.util.List;

public class OutSaleReportResponseDTOMock {

    public static SaleReportResponseDTO getDataDefault() {
        return SaleReportResponseDTO.builder()
                .companyList(List.of(OutCompanySaleDTOMock.getDataDefault()))
                .build();
    }

}
