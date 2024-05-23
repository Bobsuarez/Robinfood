package com.robinfood.app.mocks.bysegment;

import com.robinfood.core.dtos.PaymentMethodsFilterDTO;

public class PaymentMethodDTOMock {

    public static PaymentMethodsFilterDTO getDataDefault() {

        return PaymentMethodsFilterDTO.builder()
                .id(1L)
                .name("Test")
                .build();
    }
}
