package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO;

import static com.robinfood.paymentmethodsbc.sample.EntityDTOSample.getEntityDTO;
import static com.robinfood.paymentmethodsbc.sample.OriginDTOSample.getOriginDTO;
import static com.robinfood.paymentmethodsbc.sample.PaymentDTOSample.getPaymentDTO;
import static com.robinfood.paymentmethodsbc.sample.PaymentMethodDTOSample.getPaymentMethodDTO;
import static com.robinfood.paymentmethodsbc.sample.UserDTOSample.getUserDTO;

public final class PaymentRequestDTOSample {

    private PaymentRequestDTOSample() {}

    public static PaymentRequestDTO getPaymentRequestDTO() {
        return PaymentRequestDTO.builder()
            .transactionId(439079L)
            .transactionUuid("9d668189-3320-4aaa-9012-667fca977f18")
            .countryId(1L)
            .entity(getEntityDTO())
            .paymentMethod(getPaymentMethodDTO())
            .user(getUserDTO())
            .payment(getPaymentDTO())
            .origin(getOriginDTO())
            .build();
    }
}
