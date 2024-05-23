package com.robinfood.app.mocks.bysegment;

import com.robinfood.core.dtos.report.salebysegment.GetSaleBySegmentDTO;

import java.util.List;

public class GetSaleBySegmentDTOMock {

    public static GetSaleBySegmentDTO getDataDefault() {

        return GetSaleBySegmentDTO.builder()
                .companies(List.of(CompanyBySegmentDTOMock.getDataDefault()))
                .build();
    }
}
