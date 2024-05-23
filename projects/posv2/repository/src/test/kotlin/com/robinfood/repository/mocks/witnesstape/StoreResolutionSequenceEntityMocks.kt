package com.robinfood.repository.mocks.witnesstape

import com.robinfood.core.entities.posresolutionsequence.PosResolutionSequenceEntity

class StoreResolutionSequenceEntityMocks {

    val storeResolutionSequenceEntity = listOf(
        PosResolutionSequenceEntity(
            cancelledInvoices = 2,
            current = 1,
            effectiveInvoices = 20,
            endDate = "2023-02-13",
            endNumber = 3000,
            id = 1,
            initialDate = "2023-02-13",
            name = "Pos",
            posId = 1,
            prefix = "RMY",
            startNumber = 1000,
            statusId = 1,
            storeId = 27,
            typeDocument = "fiscal"
        ),
        PosResolutionSequenceEntity(
            cancelledInvoices = 2,
            current = 1,
            effectiveInvoices = 20,
            endDate = "2023-02-13",
            endNumber = 3000,
            id = 2,
            initialDate = "2023-02-13",
            name = "Pos",
            posId = 1,
            prefix = "RMY",
            startNumber = 1000,
            statusId = 1,
            storeId = 27,
            typeDocument = "fiscal"
        )
    )

    val storeResolutionSequenceIdNullEntity = listOf(
        PosResolutionSequenceEntity(
            cancelledInvoices = 2,
            current = 1,
            effectiveInvoices = 20,
            endDate = "2023-02-13",
            endNumber = 3000,
            id = null,
            initialDate = "2023-02-13",
            name = "Pos",
            posId = 1,
            prefix = "RMY",
            startNumber = 1000,
            statusId = 1,
            storeId = 27,
            typeDocument = "fiscal"
        )
    )
}