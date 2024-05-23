package com.robinfood.app.mocks;

import com.robinfood.core.dtos.transactionrequestdto.PaymentMethodDTO;

import java.math.BigDecimal;

public class PaymentMethodsDTOMocks {

    public static PaymentMethodDTO build() {
        return PaymentMethodDTO.builder()
                .id(1L)
                .value(BigDecimal.valueOf(15000))
                .build();
    }
}
