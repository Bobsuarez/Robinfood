package com.robinfood.app.mappers.salesbystore;

import com.robinfood.core.dtos.report.salebysegment.DataGenericSegmentDTO;
import com.robinfood.core.dtos.report.salebystore.CountAndValueByOrdersDTO;

public class GetValuePaymentMethodsByStoreDTOMappers {

    public static CountAndValueByOrdersDTO valueCurrentAndLastWeekToCountAndValueByOrdersDTO(
            DataGenericSegmentDTO valueCurrent,
            DataGenericSegmentDTO valueLastWeek
    ) {

        return CountAndValueByOrdersDTO.builder()
                .salesAWeekBefore(valueLastWeek)
                .salesCurrent(valueCurrent)
                .build();
    }
}
