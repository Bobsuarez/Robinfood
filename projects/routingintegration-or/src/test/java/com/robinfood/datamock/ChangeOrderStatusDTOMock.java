package com.robinfood.datamock;

import com.robinfood.dtos.changeorderstatus.ChangeOrderStatusDTO;

public class ChangeOrderStatusDTOMock {

    public static ChangeOrderStatusDTO getDefault() {

        return ChangeOrderStatusDTO.builder()
                .brandId("1")
                .notes("Se entrega la orden al domicilario")
                .orderId("1")
                .origin("local server")
                .statusCode("CHANGE_STATUS")
                .userId(1L)
                .build();
    }

    public static ChangeOrderStatusDTO getDefaultOrderIdNull() {

        return ChangeOrderStatusDTO.builder()
                .brandId("1")
                .notes("test")
                .origin("local server")
                .statusCode("CHANGE_STATUS")
                .userId(1L)
                .build();
    }
}
