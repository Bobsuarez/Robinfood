package com.robinfood.app.mocks

import com.robinfood.core.dtos.dailyreportvoucher.DailyReportVoucherRequestDTO
import java.time.LocalDate

class DailyReportVoucherRequestDTOMocks {

    val dailyReportVoucherRequestDTOMocks = DailyReportVoucherRequestDTO(
        localDateEnd = LocalDate.parse("2023-02-01"),
        localDateStart = LocalDate.parse("2023-02-01"),
        posId = 1,
        timeZone = "America/Bogota"
    )
}