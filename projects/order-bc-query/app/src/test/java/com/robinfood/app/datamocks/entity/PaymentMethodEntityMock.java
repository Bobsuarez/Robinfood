package com.robinfood.app.datamocks.entity;

import com.robinfood.core.entities.PaymentMethodEntity;
import com.robinfood.core.helpers.DateHelper;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentMethodEntityMock {

    public static PaymentMethodEntity getDataDefault() {
        return PaymentMethodEntity.builder()
                .createdAt(LocalDateTime.now())
                .id(11L)
                .icon("DAV")
                .name("daviplata")
                .nameShort("daviplata")
                .orderFlowPrintId(1)
                .statusId(1)
                .typePaymentMethodId(6)
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
