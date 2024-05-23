package com.robinfood.app.mocks.bysegment;

import com.robinfood.core.dtos.report.salebysegment.ChannelSegmentDTO;

public class ChannelSegmentDTOMock {

    public static ChannelSegmentDTO getDataDefault() {

        return ChannelSegmentDTO.builder()
                .id(1L)
                .orders(OrdersSalesSegmentDTOMock.getDataDefault())
                .build();
    }
}
