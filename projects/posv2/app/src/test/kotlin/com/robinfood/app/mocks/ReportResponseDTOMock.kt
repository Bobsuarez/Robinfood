package com.robinfood.app.mocks

import com.robinfood.core.dtos.report.ReportResponseDTO

class ReportResponseDTOMock {

    val reportResponseDTOMock = ReportResponseDTO.Builder()
        .reportByteArray("[38,39]")
        .fileName("test")
        .build()
}