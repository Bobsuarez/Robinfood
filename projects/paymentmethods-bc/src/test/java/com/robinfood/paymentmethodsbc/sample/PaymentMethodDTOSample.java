package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO.PaymentMethodDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO.PaymentMethodDTO.PaymentReferencePaymentMethod;

import java.util.List;

public final class PaymentMethodDTOSample {

    private PaymentMethodDTOSample() {}

    public static PaymentMethodDTO getPaymentMethodDTO() {
        return PaymentMethodDTO.builder()
            .id(8L)
            .installmentsNumber(1L)
            .terminalUuid("48:5F:99:02:5B:E7")
            .referencePaymentMethods(
                List.of(PaymentReferencePaymentMethod.builder().id(4).build())
            )
            .build();
    }
}
