package com.robinfood.app.mocks;

import com.robinfood.core.dtos.paymentmethodpaiddto.PaymentMethodPaidResponseDTO;

public class PaymentMethodPaidResponseDTOMock {

    public static PaymentMethodPaidResponseDTO build() {
        return PaymentMethodPaidResponseDTO.builder()
                .message("success")
                .generated(true)
                .authorizarionCode("123")
                .statusTraceName("Accepted")
                .paymentGatewayName("PayU")
                .build();
    }

    public static PaymentMethodPaidResponseDTO withStatusTraceNameValueRejected() {
        return PaymentMethodPaidResponseDTO.builder()
                .message("success")
                .generated(true)
                .authorizarionCode("123")
                .statusTraceName("Rejected")
                .paymentGatewayName("PayU")
                .build();
    }
}
