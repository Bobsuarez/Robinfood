package com.robinfood.core.mocks.dto;

import com.robinfood.core.dtos.orderbyuser.ResponseOrderDTO;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
