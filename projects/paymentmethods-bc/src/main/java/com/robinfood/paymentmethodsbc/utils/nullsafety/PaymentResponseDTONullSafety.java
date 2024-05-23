package com.robinfood.paymentmethodsbc.utils.nullsafety;

import com.robinfood.paymentmethodsbc.dto.PaymentResponseDTO;

import java.util.Optional;

import static java.util.Objects.requireNonNullElse;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public final class PaymentResponseDTONullSafety {

    private PaymentResponseDTONullSafety() {}

    public static PaymentResponseDTO validatePaymentResponseDTO(PaymentResponseDTO paymentResponseDTO) {
        return requireNonNullElse(paymentResponseDTO, PaymentResponseDTO.builder().build());
    }

    public static String getMessageBCI(PaymentResponseDTO response) {
        return Optional.ofNullable(
            validatePaymentResponseDTO(response).getMessage()
        ).orElse(EMPTY);
    }

    public static String getPaymentGatewayStatus(PaymentResponseDTO response) {
        return Optional.ofNullable(
            validatePaymentResponseDTO(response).getPaymentGatewayStatus()
        ).orElse(EMPTY);
    }

    public static String getPaymentGatewayLevelCategory(PaymentResponseDTO response) {
        return Optional.ofNullable(
            validatePaymentResponseDTO(response).getPaymentGatewayLevelCategory()
        ).orElse(EMPTY);
    }
}
