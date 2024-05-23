package com.robinfood.app.datamocks.dto.core;

import com.robinfood.core.dtos.OrderPaymentDTO;

import java.util.List;

public class OrderPaymentDTOMock {

    public static List<OrderPaymentDTO> getOrderPaymentDTOList() {

        return List.of(OrderPaymentDTO.builder()
                .orderId(1L)
                .id(1L)
                .tax(8900.0)
                .value(8900.0)
                .paymentMethodId(1L)
                .detail(null).discount(0.0)
                .subtotal(8900.0).
                build());
    }
}
