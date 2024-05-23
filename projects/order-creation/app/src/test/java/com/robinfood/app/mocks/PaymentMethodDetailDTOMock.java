package com.robinfood.app.mocks;

import com.robinfood.core.dtos.transactionrequestdto.PaymentMethodDetailDTO;

public class PaymentMethodDetailDTOMock {

    public static PaymentMethodDetailDTO build() {
        return PaymentMethodDetailDTO.builder()
                .creditCard(11752L)
                .installmentsNumber(1L)
                .selfManagementCode("00:00:00:00:00:00")
                .build();
    }
}
