package com.robinfood.paymentmethodsbc.utils.nullsafety;

import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentInitResponseDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentInitResponseDTO.StatusDTO;

import static java.util.Objects.requireNonNullElse;

public final class PaymentInitResponseDTONullSafety {

    private PaymentInitResponseDTONullSafety() {}

    public static PaymentInitResponseDTO validatePaymentInitResponseDTO(
        PaymentInitResponseDTO paymentInitResponseDTO
    ) {
        return requireNonNullElse(paymentInitResponseDTO, PaymentInitResponseDTO.builder().build());
    }

    public static StatusDTO validateStatusDTO(StatusDTO statusDTO) {
        return requireNonNullElse(statusDTO, StatusDTO.builder().build());
    }
}
