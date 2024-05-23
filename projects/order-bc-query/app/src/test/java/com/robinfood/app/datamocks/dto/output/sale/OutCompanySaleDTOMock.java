package com.robinfood.app.datamocks.dto.output.sale;

import com.robinfood.core.dtos.report.sale.CompanySaleDTO;

public class OutCompanySaleDTOMock {

    public static CompanySaleDTO getDataDefault() {
        return CompanySaleDTO.builder()
                .id(1L)
                .ordersSales(OutOrdersSalesDTOMock.getDataDefault())
                .build();
    }
}
