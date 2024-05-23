package com.robinfood.app.datamocks.dto.output;

import com.robinfood.core.dtos.GetOrderDetailPaymentMethodDTO;

public class GetOrderDetailPaymentMethodDTOMock {

    public static GetOrderDetailPaymentMethodDTO getDataDefault(){

        return GetOrderDetailPaymentMethodDTO.builder()
                .discount(0.0)
                .id(11L)
                .subtotal(8900.0)
                .name("daviplata")
                .orderId(1L)
                .originId(1L)
                .tax(0.0)
                .value(8900.0)
                .build();
    }
}
