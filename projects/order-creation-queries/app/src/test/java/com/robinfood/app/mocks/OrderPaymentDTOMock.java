package com.robinfood.app.mocks;

import com.robinfood.core.dtos.orderpayment.OrderPaymentDTO;

import java.util.List;

public final class OrderPaymentDTOMock {

    public static List<OrderPaymentDTO> getDataDefault() {

        return List.of(OrderPaymentDTO.builder()
                .id(1)
                .name("cash")
                .shortName("cash")
                .transactions(4)
                .typeId("1")
                .value(77900.0)
                .build());
    }
}
