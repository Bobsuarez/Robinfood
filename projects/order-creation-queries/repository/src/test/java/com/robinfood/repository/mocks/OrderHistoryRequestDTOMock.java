package com.robinfood.repository.mocks;

import com.robinfood.core.dtos.orderhistory.request.OrderHistoryRequestDTO;

import java.time.LocalDate;
import java.util.List;

public class OrderHistoryRequestDTOMock {

    public static OrderHistoryRequestDTO getDataDefault() {

        return OrderHistoryRequestDTO.builder()
                .channelsId(List.of(1L))
                .currentPage(1)
                .isDelivery(Boolean.TRUE)
                .localDateEnd(LocalDate.now())
                .localDateStart(LocalDate.now())
                .perPage(1)
                .searchText("")
                .storeId(1L)
                .timeZone("America/Bogota")
                .build();
    }

}
