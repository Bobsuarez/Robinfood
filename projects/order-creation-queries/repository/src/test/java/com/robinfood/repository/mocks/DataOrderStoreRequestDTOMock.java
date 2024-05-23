package com.robinfood.repository.mocks;

import com.robinfood.core.dtos.ordersstore.DataOrderStoreRequestDTO;

import java.time.LocalDate;

public class DataOrderStoreRequestDTOMock {

    public static DataOrderStoreRequestDTO getDataDefault() {
        return DataOrderStoreRequestDTO.builder()
                .localDateStart(LocalDate.now())
                .localDateEnd(LocalDate.now())
                .storeId(1L)
                .timeZone("America/Bogotá")
                .build();
    }
}
