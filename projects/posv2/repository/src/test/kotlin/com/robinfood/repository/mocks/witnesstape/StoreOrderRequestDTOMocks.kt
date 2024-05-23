package com.robinfood.repository.mocks.witnesstape

import com.robinfood.core.dtos.witnesstape.StoreOrderRequestDTO
import java.time.LocalDate

class StoreOrderRequestDTOMocks {

    val storeOrderRequestDTOMocks = StoreOrderRequestDTO(
        localDateEnd = LocalDate.parse("2023-02-13"),
        localDateStart = LocalDate.parse("2023-02-13"),
        storeId = 1,
        timeZone = "America/Bogota"
    )
}