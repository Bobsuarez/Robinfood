package com.robinfood.app.mocks.statusorderhistory;

import com.robinfood.core.dtos.statusorderhistory.StatusOrderHistoryDTO;

public final class StatusOrderHistoryDTOMock {

    public static StatusOrderHistoryDTO getDataDefault() {

        return StatusOrderHistoryDTO.builder()
                .createdAt("2023-04-03T22:05:17")
                .id(1L)
                .observation("Sin notas")
                .userId(1L)
                .build();
    }
}
