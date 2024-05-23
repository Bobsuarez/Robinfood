package com.robinfood.repository.mocks;

import com.robinfood.core.dtos.orderbyuser.ResponseOrderDTO;

import java.util.List;

public class ResponseActiveOrderMock {

    public static List<ResponseOrderDTO> getResponseActiveOrderMock() {
        return List.of(
            getResponseOrder()
        );
    }

    private static ResponseOrderDTO getResponseOrder() {
        return ResponseOrderDTO.builder()
            .id(1L)
            .uid("12345abcde")
            .paid(true)
            .flowId(1L)
            .platformId(1L)
            .timezone("UTC")
            .build();
    }
}
