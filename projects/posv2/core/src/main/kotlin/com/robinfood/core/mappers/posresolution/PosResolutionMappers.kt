package com.robinfood.core.mappers.posresolution

import com.robinfood.core.dtos.posresolutionsequence.PosResolutionSequenceDTO
import com.robinfood.core.entities.posresolutionsequence.PosResolutionSequenceEntity

fun PosResolutionSequenceEntity.toPosResolutionEntityToPosResolutionDTO(): PosResolutionSequenceDTO? {
    return if (id == null) {
        null
    } else PosResolutionSequenceDTO(
        cancelledInvoices = cancelledInvoices,
        current = current,
        effectiveInvoices = effectiveInvoices,
        endDate = endDate,
        endNumber = endNumber,
        id = id,
        initialDate = initialDate,
        name = name,
        posId = posId,
        prefix = prefix,
        startNumber = startNumber,
        statusId = statusId,
        storeId = storeId,
        typeDocument = typeDocument
    )
}