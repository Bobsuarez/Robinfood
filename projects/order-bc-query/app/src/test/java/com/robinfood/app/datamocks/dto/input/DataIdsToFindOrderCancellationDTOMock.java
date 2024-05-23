package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.report.ordercancellation.DataToSearchIdsCanceledOrdersDTO;

import java.time.LocalDate;
import java.util.List;

public class DataIdsToFindOrderCancellationDTOMock {

    public static DataToSearchIdsCanceledOrdersDTO getDefault() {

        return DataToSearchIdsCanceledOrdersDTO.builder()
                .brandsIdsDTO(List.of(1L))
                .companyIdDTO(1L)
                .currentPageDTO(1)
                .idCustomFilterDTO(1L)
                .localDateEndDTO(LocalDate.now())
                .localDateStartDTO(LocalDate.now())
                .originIdsDTO(List.of(1L))
                .paymentMethodIdsDTO(List.of(1L))
                .perPageDTO(1)
                .statusCodeDTO("ORDER_CREATED")
                .storesIdsDTO(List.of(1L))
                .timeZoneDTO("America/Bogota")
                .valueCustomFilterDTO("78594")
                .build();
    }
}
