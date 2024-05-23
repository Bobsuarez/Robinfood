package com.robinfood.app.mappers.salesbysegment;

import com.robinfood.core.dtos.report.salebysegment.ChannelSegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.OrdersSalesSegmentDTO;

public class GetChannelSaleDTOMappers {

    public static ChannelSegmentDTO channelIdAndOrderSalesSegmentDTOToChannelSegmentDTO(
            Long channelId,
            OrdersSalesSegmentDTO ordersSalesDTO
    ) {

        return ChannelSegmentDTO.builder()
                .idChannel(channelId)
                .ordersSalesDTO(ordersSalesDTO)
                .build();
    }
}
