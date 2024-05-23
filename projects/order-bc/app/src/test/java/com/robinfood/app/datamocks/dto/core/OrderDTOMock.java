package com.robinfood.app.datamocks.dto.core;

import com.robinfood.core.dtos.OrderDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class OrderDTOMock {

    public static OrderDTO build() {
        return new OrderDTO(
            1L,
            1L,
            "Brand name test",
            1L,
            LocalDateTime.now(),
            "COP",
            3L,
            3000.00,
            1L,
            LocalDate.now(),
            LocalTime.now(),
            1,
            LocalDate.now(),
            "321211",
            "232131",
            1L,
            "Origin name test",
            Boolean.TRUE,
            "0000000",
            1L,
                Boolean.FALSE,
                1L,
                1L,
                "Store name test",
                10000.00,
                0.0,
                21321L,
                BigDecimal.ZERO,
                10000.00,
                "djdkcddwc-23213-mdeiwm",
                "685ffb1a-2084-4e87-95fb-daea691d5a09",
                1L,
                1L
        );
    }

}
