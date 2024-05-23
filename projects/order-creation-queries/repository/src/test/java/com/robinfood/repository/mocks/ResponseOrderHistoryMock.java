package com.robinfood.repository.mocks;

import com.robinfood.core.dtos.EntityDTO;
import com.robinfood.core.dtos.PaginationDTO;
import com.robinfood.core.dtos.orderbyuser.ResponseOrderDTO;

import java.util.List;

public class ResponseOrderHistoryMock {

    public static EntityDTO<ResponseOrderDTO> getEntityResponseOrderHistoryMock() {
        return EntityDTO.<ResponseOrderDTO>builder()
            .items(getResponseOrderHistoryMock())
            .pagination(
                PaginationDTO.builder()
                    .perPage(10)
                    .total(1L)
                    .lastPage(1)
                    .page(1)
                    .build()
            )
            .build();
    }

    private static List<ResponseOrderDTO> getResponseOrderHistoryMock() {
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
