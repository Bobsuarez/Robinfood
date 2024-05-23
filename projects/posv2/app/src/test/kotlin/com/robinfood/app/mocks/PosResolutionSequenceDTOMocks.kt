package com.robinfood.app.mocks

import com.robinfood.core.dtos.posresolutionsequence.PosResolutionSequenceDTO

class PosResolutionSequenceDTOMocks {

    val posResolutionSequenceDTO = PosResolutionSequenceDTO(
        cancelledInvoices = 2,
        current = 1,
        effectiveInvoices = 20,
        endDate = "2023-02-13",
        endNumber = 3000,
        id = 1,
        initialDate = "2023-02-13",
        name = "Caja 1 - Test",
        posId = 1,
        prefix = "RMY",
        startNumber = 1000,
        statusId = 1,
        storeId = 27,
        typeDocument = "fiscal"
    )

    val posResolutionSequenceLengthDTO = PosResolutionSequenceDTO(
        cancelledInvoices = 2,
        current = 1,
        effectiveInvoices = 20,
        endDate = "2023-02-13",
        endNumber = 3000,
        id = 1,
        initialDate = "2023-02-13",
        name = "Caja 1",
        posId = 1,
        prefix = "RMY",
        startNumber = 1000,
        statusId = 1,
        storeId = 27,
        typeDocument = "fiscal"
    )

    val posResolutionSequenceDTONull = PosResolutionSequenceDTO(
        cancelledInvoices = 2,
        current = 1,
        effectiveInvoices = 20,
        endDate = "2023-02-13",
        endNumber = 3000,
        id = 1,
        initialDate = "2023-02-13",
        name = null,
        posId = 1,
        prefix = "RMY",
        startNumber = 1000,
        statusId = 1,
        storeId = 27,
        typeDocument = "fiscal"
    )
}