package com.robinfood.app.mocks.dailyreportevoucher

import com.robinfood.core.dtos.posrelatedtoastore.StorePosDTO

class StorePosDTOMock {

    val storePosDTOs = listOf(
        StorePosDTO(
            1L,
            "pos 1",
            ResolutionDTOsMock().resolutionDTOs
        )
    )
}
