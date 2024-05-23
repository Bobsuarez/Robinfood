package com.robinfood.app.mocks.witnesstape

import com.robinfood.core.dtos.posresolutionsequence.PosResolutionSequenceDTO

class StoreResolutionSequenceDTOMocks {

    val storeResolutionSequenceEntities = listOf(
        PosResolutionSequenceDTO(
            cancelledInvoices = 2,
            current = 1,
            effectiveInvoices = 20,
            endDate = "2023-02-13",
            endNumber = 3000,
            id = 1,
            initialDate = "2023-02-13",
            name = "Caja 1 - test",
            posId = 1,
            prefix = "RMY",
            startNumber = 1000,
            statusId = 1,
            storeId = 27,
            typeDocument = "fiscal"
        ),
        PosResolutionSequenceDTO(
            cancelledInvoices = 2,
            current = 1,
            effectiveInvoices = 20,
            endDate = "2023-02-13",
            endNumber = 3000,
            id = 2,
            initialDate = "2023-02-13",
            name = "Caja 2 - Test",
            posId = 2,
            prefix = "RMY",
            startNumber = 1000,
            statusId = 1,
            storeId = 27,
            typeDocument = "fiscal"
        )
    )
}