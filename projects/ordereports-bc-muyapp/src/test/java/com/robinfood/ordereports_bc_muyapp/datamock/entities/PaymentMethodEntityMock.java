package com.robinfood.ordereports_bc_muyapp.datamock.entities;

import com.robinfood.ordereports_bc_muyapp.models.entities.PaymentMethodEntity;

import java.time.LocalDateTime;

public class PaymentMethodEntityMock {

    public static PaymentMethodEntity getDataDefault() {

        return PaymentMethodEntity.builder()
                .id((short) 1)
                .typePaymentMethodId((short) 2)
                .orderFlowPrintId((short) 3)
                .statusId((short) 4)
                .name("Credit Card")
                .nameShort("CC")
                .icon("credit_card_icon")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
