package com.robinfood.app.mocks.bysegment;

import com.robinfood.core.dtos.report.salebysegment.CompanyBySegmentDTO;

import java.util.List;

public class CompanyBySegmentDTOMock {

    public static CompanyBySegmentDTO getDataDefault() {

        return CompanyBySegmentDTO.builder()
                .brands(List.of(BrandSegmentDTOMock.getDataDefault()))
                .channels(List.of(ChannelSegmentDTOMock.getDataDefault()))
                .id(1L)
                .paymentMethods(List.of(PaymentMethodSegmentDTOMock.getDataDefault()))
                .stores(List.of(StoreSegmentDTOMock.getDataDefault()))
                .build();
    }
}
