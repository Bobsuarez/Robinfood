package com.robinfood.app.datamocks.dto.core;

import com.robinfood.core.dtos.OrderPaymentDTO;

public class OrderPaymentDTOMock {

    public static OrderPaymentDTO getDataDefault() {
        return OrderPaymentDTO.builder()
                .detail(null)
                .discount(0.0)
                .id(1L)
                .orderId(1L)
                .originId(10L)
                .paymentMethodId(4L)
                .subtotal(1000.0)
                .tax(0.0)
                .value(1900.0)
                .build();
    }

}
