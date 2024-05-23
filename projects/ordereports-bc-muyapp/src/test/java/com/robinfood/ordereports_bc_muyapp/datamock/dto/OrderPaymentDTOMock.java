package com.robinfood.ordereports_bc_muyapp.datamock.dto;

import com.robinfood.ordereports_bc_muyapp.dto.OrderPaymentDTO;

public class OrderPaymentDTOMock {

    public static OrderPaymentDTO getDataDefault() {

        return OrderPaymentDTO.builder()
                .detail(OrderPaymentDetailDTOMock.getDataDefault())
                .discount(5.0)
                .id(1L)
                .orderId(12345)
                .originId((short) 1)
                .paymentMethodId((short) 2)
                .subtotal(100.0)
                .tax(10.0)
                .value(110.0)
                .build();
    }
}
