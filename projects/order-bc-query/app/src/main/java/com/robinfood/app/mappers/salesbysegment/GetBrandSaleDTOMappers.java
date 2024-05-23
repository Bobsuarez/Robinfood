package com.robinfood.app.mappers.salesbysegment;

import com.robinfood.core.dtos.report.salebysegment.BrandSegmentDTO;
import com.robinfood.core.dtos.report.salebysegment.OrdersSalesSegmentDTO;

public class GetBrandSaleDTOMappers {

    public static BrandSegmentDTO brandIdAndOrderSalesDTOTOBrandSegmentDTO(
            Long idBrand,
            OrdersSalesSegmentDTO ordersSalesDTO
    ) {

        return BrandSegmentDTO.builder()
                .idBrand(idBrand)
                .ordersSalesDTO(ordersSalesDTO)
                .build();
    }
}
