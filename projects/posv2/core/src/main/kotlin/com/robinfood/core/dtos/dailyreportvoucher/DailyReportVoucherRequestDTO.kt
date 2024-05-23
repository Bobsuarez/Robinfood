package com.robinfood.core.dtos.dailyreportvoucher

import java.time.LocalDate

data class DailyReportVoucherRequestDTO (

    val localDateEnd: LocalDate,
    val localDateStart: LocalDate,
    val posId: Long,
    val timeZone: String
)