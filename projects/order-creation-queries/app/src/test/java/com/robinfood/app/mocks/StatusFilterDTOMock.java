package com.robinfood.app.mocks;

import com.robinfood.core.dtos.StatusCustomFilterDTO;

import java.util.List;

public class StatusFilterDTOMock {

    public static List<StatusCustomFilterDTO> getDataDefaultList() {

        return List.of(
                StatusCustomFilterDTO.builder()
                        .code("ORDER_PAID")
                        .id(1l)
                .build(),
                StatusCustomFilterDTO.builder()
                        .code("ORDER_CANCELED")
                        .id(2l)
                        .build()
        );
    }
}
