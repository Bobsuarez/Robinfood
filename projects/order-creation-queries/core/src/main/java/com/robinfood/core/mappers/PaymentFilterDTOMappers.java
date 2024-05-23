package com.robinfood.core.mappers;

import com.robinfood.core.dtos.PaymentMethodsFilterDTO;

public final class PaymentFilterDTOMappers {

    private PaymentFilterDTOMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static PaymentMethodsFilterDTO toPaymentMethodDTO(Long idPayment, String namePayment) {

        return PaymentMethodsFilterDTO.builder()
                .id(idPayment)
                .name(namePayment)
                .build();
    }
}
