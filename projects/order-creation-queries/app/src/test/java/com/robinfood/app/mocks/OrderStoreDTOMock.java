package com.robinfood.app.mocks;

import com.robinfood.core.dtos.ordersstore.OrderStoreDTO;

import java.util.List;

public class OrderStoreDTOMock {

    public static List<OrderStoreDTO> getDataDefault() {
        return List.of(OrderStoreDTO.builder()
                .compensation(100000.0)
                .discounts(100000.0)
                .grossValue(100000.0)
                .id(1L)
                .netValue(100000.0)
                .orderInvoiceNumber("64857498")
                .statusCode("ORDER_CREATED")
                .statusId(1L)
                .taxes(2.0)
                .uuid("56765462-91d5-4edd-9064-48e1e28bee0b")
                .build());
    }
}
