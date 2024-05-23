package com.robinfood.app.datamocks.dto.output.sale;

import com.robinfood.core.dtos.report.sale.SalesAWeekBeforeDTO;

import java.math.BigDecimal;

public class OutSalesAWeekBeforeDTOMock {

    public static SalesAWeekBeforeDTO getDataDefault() {
        return SalesAWeekBeforeDTO.builder()
                .value(new BigDecimal("42.325"))
                .build();
    }

}
