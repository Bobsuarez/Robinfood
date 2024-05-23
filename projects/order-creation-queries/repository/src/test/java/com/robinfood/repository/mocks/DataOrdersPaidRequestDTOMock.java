package com.robinfood.repository.mocks;

import com.robinfood.core.dtos.orderspaid.DataOrdersPaidRequestDTO;

import java.time.LocalDate;
import java.util.List;

public class DataOrdersPaidRequestDTOMock {

    public static DataOrdersPaidRequestDTO getDataDefault() {
        return DataOrdersPaidRequestDTO.builder()
                .brandIds(List.of(1L,1L))
                .companyId(1L)
                .currentPage(1)
                .idCustomFilter(1L)
                .localDateStart(LocalDate.now())
                .localDateEnd(LocalDate.now())
                .originIds(List.of(1L,1L))
                .paymentMethodIds(List.of(1L,1L))
                .perPage(10)
                .statusCode("ORDER_PAID")
                .storeIds(List.of(1L,1L))
                .timezone("America/Bogotá")
                .valueCustomFilter("1234")
                .build();
    }
}
