package com.robinfood.repository.mocks;

import com.robinfood.core.entities.PaymentMethodsEntity;

public final class PaymentMethodsEntityDTOMock {

    public static PaymentMethodsEntity getDataDefault() {

        return PaymentMethodsEntity.builder()
                .createdAt("2022-03-25T03:00:00")
                .icon("")
                .id(0L)
                .name("pos_debit_card")
                .nameShort("")
                .orderFlowPrintId(0)
                .statusId(0)
                .typePaymentMethodId(0)
                .updatedAt("2022-03-25T03:00:00")
                .build();
    }
}
