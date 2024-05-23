package com.robinfood.core.dtos.witnesstape

import java.time.LocalDate

data class StoreOrderRequestDTO (

    val localDateEnd: LocalDate,
    val localDateStart: LocalDate,
    val storeId: Long,
    val timeZone: String
)