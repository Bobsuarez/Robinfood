package com.robinfood.paymentmethodsbc.utils.nullsafety;

import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO.OriginDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO.PaymentMethodDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO.PaymentMethodDTO.PaymentReferencePaymentMethod;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO.UserDTO;

import java.util.List;

import static java.util.Objects.requireNonNullElse;

public final class PaymentRequestDTONullSafety {

    private PaymentRequestDTONullSafety() {}

    public static OriginDTO validateOriginDTO(OriginDTO originDTO) {
        return requireNonNullElse(originDTO, OriginDTO.builder().build());
    }

    public static UserDTO validateUserDTO(UserDTO userDTO) {
        return requireNonNullElse(userDTO, UserDTO.builder().build());
    }

    public static PaymentMethodDTO validatePaymentMethodDTO(PaymentMethodDTO paymentMethodDTO) {
        return requireNonNullElse(paymentMethodDTO, PaymentMethodDTO.builder().build());
    }

    public static List<PaymentReferencePaymentMethod> validatePaymentReferencePaymentMethodList(
        List<PaymentReferencePaymentMethod> paymentReferencePaymentMethods
    ) {
        return requireNonNullElse(paymentReferencePaymentMethods, List.of());
    }

    public static PaymentReferencePaymentMethod validatePaymentReferencePaymentMethod(
        PaymentReferencePaymentMethod paymentReferencePaymentMethods
    ) {
        return requireNonNullElse(paymentReferencePaymentMethods, PaymentReferencePaymentMethod.builder().build());
    }
}
