package com.robinfood.app.mappers.salesbysegment;

import com.robinfood.core.dtos.report.salebysegment.DataGenericSegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.OrdersSalesSegmentDTO;

public class GetOrdersSalesDTOMappers {

    public static OrdersSalesSegmentDTO valueCurrentAndLastWeekToOrderSalesSegmentDTO(
            DataGenericSegmentDTO valueCurrent,
            DataGenericSegmentDTO valueLastWeek
    ) {

        return OrdersSalesSegmentDTO.builder()
                .salesAWeekBefore(valueLastWeek)
                .salesCurrent(valueCurrent)
                .build();
    }
}
