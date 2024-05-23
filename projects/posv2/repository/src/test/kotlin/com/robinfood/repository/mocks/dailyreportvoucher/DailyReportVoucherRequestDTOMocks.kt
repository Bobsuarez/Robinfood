package com.robinfood.repository.mocks.dailyreportvoucher

import com.robinfood.core.dtos.dailyreportvoucher.DailyReportVoucherRequestDTO
import java.time.LocalDate

class DailyReportVoucherRequestDTOMocks {

    val dailyReportVoucherRequestDTOMocks = DailyReportVoucherRequestDTO(
        localDateEnd = LocalDate.parse("2023-02-13"),
        localDateStart = LocalDate.parse("2023-02-13"),
        posId = 1,
        timeZone = "America/Bogota"
    )
}