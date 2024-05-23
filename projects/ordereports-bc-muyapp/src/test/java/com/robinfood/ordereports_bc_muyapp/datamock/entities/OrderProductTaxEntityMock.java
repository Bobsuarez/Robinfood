package com.robinfood.ordereports_bc_muyapp.datamock.entities;

import com.robinfood.ordereports_bc_muyapp.models.entities.OrderProductTaxEntity;

import java.time.LocalDateTime;


public class OrderProductTaxEntityMock {

    public static OrderProductTaxEntity getDataDefault() {
        return OrderProductTaxEntity.builder()
                .articleId(1L)
                .articleTypeId(2L)
                .createdAt(LocalDateTime.now())
                .dicTaxId(3L)
                .familyTaxTypeId(4L)
                .id(5L)
                .orderFinalProductId(1L)
                .orderId(7L)
                .taxPrice(8.5)
                .taxTypeId(9L)
                .taxTypeName("Tax Name")
                .taxValue(10.0)
                .updatedAt(LocalDateTime.now())
                .build();
    }
}