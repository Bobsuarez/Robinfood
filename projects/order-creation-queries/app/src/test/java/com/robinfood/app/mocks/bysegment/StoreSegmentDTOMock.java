package com.robinfood.app.mocks.bysegment;

import com.robinfood.core.dtos.report.salebysegment.StoreSegmentDTO;

public class StoreSegmentDTOMock {

    public static StoreSegmentDTO getDataDefault() {

        return StoreSegmentDTO.builder()
                .id(1L)
                .orders(OrdersSalesSegmentDTOMock.getDataDefault())
                .build();
    }
}
