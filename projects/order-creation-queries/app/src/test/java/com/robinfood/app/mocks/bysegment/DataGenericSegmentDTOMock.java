package com.robinfood.app.mocks.bysegment;

import com.robinfood.core.dtos.report.salebysegment.DataGenericSegmentDTO;

import java.math.BigDecimal;

public class DataGenericSegmentDTOMock {

    public static DataGenericSegmentDTO getDataDefault() {

        return DataGenericSegmentDTO.builder()
                .value(new BigDecimal("145000.0"))
                .build();
    }
}
