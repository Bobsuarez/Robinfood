package com.robinfood.app.mappers.salesbysegment;

import com.robinfood.core.dtos.report.salebysegment.OrdersSalesSegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.StoreSegmentDTO;

public class GetStoreSaleDTOMappers {

    public static StoreSegmentDTO ordersSalesDTOToStoreSegmentDTO(Long idBrand, OrdersSalesSegmentDTO ordersSalesDTO) {

        return StoreSegmentDTO.builder()
                .idStore(idBrand)
                .ordersSalesDTO(ordersSalesDTO)
                .build();
    }
}
