package com.robinfood.app.datamocks.dto.output.sale;

import com.robinfood.core.dtos.report.sale.SalesCurrentDTO;

import java.math.BigDecimal;

public class OutSalesCurrentDTOMock {

    public static SalesCurrentDTO getDataDefault() {
        return SalesCurrentDTO.builder()
                .value(new BigDecimal("42.325"))
                .build();
    }

}
