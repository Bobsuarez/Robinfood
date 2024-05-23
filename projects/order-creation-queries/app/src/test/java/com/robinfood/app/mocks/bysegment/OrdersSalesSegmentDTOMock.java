package com.robinfood.app.mocks.bysegment;

import com.robinfood.core.dtos.report.salebysegment.OrdersSalesSegmentDTO;

public class OrdersSalesSegmentDTOMock {

    public static OrdersSalesSegmentDTO getDataDefault() {

        return OrdersSalesSegmentDTO.builder()
                .salesAWeekBefore(DataGenericSegmentDTOMock.getDataDefault())
                .salesCurrent(DataGenericSegmentDTOMock.getDataDefault())
                .build();
    }
}
