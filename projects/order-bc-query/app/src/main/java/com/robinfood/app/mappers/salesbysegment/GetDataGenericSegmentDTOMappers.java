package com.robinfood.app.mappers.salesbysegment;

import com.robinfood.core.dtos.report.salebysegment.DataGenericSegmentDTO;

import java.math.BigDecimal;

public class GetDataGenericSegmentDTOMappers {

    public static DataGenericSegmentDTO valueSaleToDataGenericSegmentDTO(BigDecimal dataSale) {

        return DataGenericSegmentDTO.builder()
                .value(dataSale)
                .build();
    }

    public static DataGenericSegmentDTO valueSaleToDataGenericSegmentDTO(BigDecimal dataSale, Long counter) {

        return DataGenericSegmentDTO.builder()
                .counter(counter)
                .value(dataSale)
                .build();
    }
}
