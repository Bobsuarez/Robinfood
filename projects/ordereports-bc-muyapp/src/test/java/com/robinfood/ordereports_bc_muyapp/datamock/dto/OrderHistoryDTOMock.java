package com.robinfood.ordereports_bc_muyapp.datamock.dto;

import com.robinfood.ordereports_bc_muyapp.dto.OrderHistoryDTO;

import java.time.LocalDateTime;

public class OrderHistoryDTOMock {

    public static OrderHistoryDTO getDataDefault() {

        return OrderHistoryDTO.builder()
                .createdAt(LocalDateTime.now())
                .date(LocalDateTime.of(2024, 6, 20, 12, 0))
                .id(1001L)
                .observation("This is a sample observation.")
                .orderId(12345)
                .orderStatusId((short) 1)
                .userId(2001L)
                .build();
    }
}
