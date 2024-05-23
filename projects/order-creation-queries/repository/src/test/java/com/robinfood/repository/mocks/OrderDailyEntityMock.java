package com.robinfood.repository.mocks;

import com.robinfood.core.entities.OrderDailyEntity;
import com.robinfood.core.entities.OrderDetailUserEntity;
import com.robinfood.core.entities.services.OriginEntity;

public final class OrderDailyEntityMock {

    public static OrderDailyEntity getDataDefault() {

        return OrderDailyEntity.builder()
                .brandId(1L)
                .createdAt("")
                .deliveryTypeId(1L)
                .id(1L)
                .orderInvoiceNumber("0001")
                .orderNumber("0001")
                .origin(OriginEntityMock.getDataDefault())
                .total(1.00)
                .build();
    }
}
