package com.robinfood.app.mocks;

import com.robinfood.core.dtos.transactionresponsedto.OrderResponseDTO;

import java.math.BigDecimal;

public class OrderResponseDTOMocks {

    public static OrderResponseDTO build() {
        return OrderResponseDTO.builder()
                .discountPrice(new BigDecimal(1))
                .id(1L)
                .subtotal(new BigDecimal(1))
                .taxPrice(new BigDecimal(1))
                .total(new BigDecimal(1))
                .uid("1234")
                .build();
    }

}
