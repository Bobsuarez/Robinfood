package com.robinfood.app.datamocks.dto.core;

import com.robinfood.core.dtos.OrderDiscountDTO;

public class OrderDiscountDTOMock {

    public static OrderDiscountDTO build() {
        return new OrderDiscountDTO(
            1L,
            2000.00,
            1L,
            1L,
            1212L,
            211L
        );
    }

}
