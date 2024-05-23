package com.robinfood.app.mocks;

import com.robinfood.core.dtos.OrderDailyDTO;

public final class OrderDailyDTOMock {

    public static OrderDailyDTO getDataDefault() {

        return OrderDailyDTO.builder()
                .brand(BrandDTOMock.getDataDefault())
                .brandId(1L)
                .createdAt("2022-09-19T15:48:58")
                .deliveryTypeId(1L)
                .id(1L)
                .orderInvoiceNumber("0001")
                .orderNumber("0001")
                .origin(OriginDTOMock.getDataDefault())
                .total(1.00)
                .build();
    }

    public static OrderDailyDTO getDataDefaultNotFoundBrand() {

        return OrderDailyDTO.builder()
                .brand(BrandDTOMock.getDataDefault())
                .brandId(12L)
                .createdAt("2022-09-19T15:48:58")
                .deliveryTypeId(1L)
                .id(1L)
                .orderInvoiceNumber("0001")
                .orderNumber("0001")
                .origin(OriginDTOMock.getDataDefault())
                .total(1.00)
                .build();
    }
}
