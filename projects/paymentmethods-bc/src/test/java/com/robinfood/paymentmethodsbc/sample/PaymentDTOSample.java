package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO.PaymentDTO;

import java.math.BigDecimal;

public final class PaymentDTOSample {

    private PaymentDTOSample() {}

    public static PaymentDTO getPaymentDTO() {
        return PaymentDTO.builder()
            .subtotal(new BigDecimal("12870.3704"))
            .tax(new BigDecimal("1029.6296"))
            .total(new BigDecimal("13900"))
            .build();
    }
}
