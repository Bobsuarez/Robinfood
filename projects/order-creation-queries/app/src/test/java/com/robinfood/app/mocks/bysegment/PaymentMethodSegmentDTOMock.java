package com.robinfood.app.mocks.bysegment;

import com.robinfood.core.dtos.report.salebysegment.PaymentMethodSegmentDTO;

public class PaymentMethodSegmentDTOMock {

    public static PaymentMethodSegmentDTO getDataDefault() {

        return PaymentMethodSegmentDTO.builder()
                .id(1L)
                .name("test")
                .orders(OrdersSalesSegmentDTOMock.getDataDefault())
                .build();
    }
}
