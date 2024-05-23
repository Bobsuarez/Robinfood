package com.robinfood.ordereports_bc_muyapp.datamock.dto;

import com.robinfood.ordereports_bc_muyapp.dto.OrderDiscountDTO;

public class OrderDiscountDTOMock {

    public static OrderDiscountDTO getDataDefault() {

        return OrderDiscountDTO.builder()
                .discountId(1L)
                .orderDiscountTypeId(1L)
                .discountValue(1.0)
                .orderId(1)
                .id(1L)
                .orderFinalProductId(1L)
                .build();
    }
}
