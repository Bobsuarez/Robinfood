package com.robinfood.app.mocks;

import com.robinfood.core.dtos.PaymentMethodsFilterDTO;

public final class PaymentMethodsFilterDTOMock {

    public static PaymentMethodsFilterDTO getDataDefault(){

        return PaymentMethodsFilterDTO.builder()
                .id(1L)
                .name("cash")
                .build();
    }
}
