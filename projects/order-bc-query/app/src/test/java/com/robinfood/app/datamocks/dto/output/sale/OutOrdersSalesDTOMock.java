package com.robinfood.app.datamocks.dto.output.sale;

import com.robinfood.core.dtos.report.sale.OrdersSalesDTO;

public class OutOrdersSalesDTOMock {

    public static OrdersSalesDTO getDataDefault() {
        return OrdersSalesDTO.builder()
                .salesAWeekBeforeDto(OutSalesAWeekBeforeDTOMock.getDataDefault())
                .salesCurrentDto(OutSalesCurrentDTOMock.getDataDefault())
                .build();
    }
}
