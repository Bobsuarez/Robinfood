package com.robinfood.app.mocks.bysegment;

import com.robinfood.core.dtos.report.salebysegment.BrandSegmentDTO;

public class BrandSegmentDTOMock {

    public static BrandSegmentDTO getDataDefault() {

        return BrandSegmentDTO.builder()
                .id(1L)
                .orders(OrdersSalesSegmentDTOMock.getDataDefault())
                .build();
    }
}
