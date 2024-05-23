package com.robinfood.core.mappers.witnesstape

import com.robinfood.core.dtos.posresolutionsequence.PosResolutionSequenceDTO
import com.robinfood.core.dtos.witnesstape.WitnessTapePosDTO
import com.robinfood.core.entities.posresolutionsequence.PosResolutionSequenceEntity

fun PosResolutionSequenceEntity.toStoreResolutionEntityToPosResolutionDTO(): PosResolutionSequenceDTO? {
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

fun PosResolutionSequenceDTO.toWitnessTapePosDTOs(): WitnessTapePosDTO {
    val position = name!!.indexOf("-")
    val box = if (position == -1) name
    else position?.let { name.substring(0, it) }

    return WitnessTapePosDTO(
        box = box,
        finalNumber = "$prefix $endNumber",
        initialNumber = "$prefix $startNumber",
        posId= posId,
        prefix = prefix,
        totalAnnulment = cancelledInvoices.toInt(),
        totalDocEquivalent = effectiveInvoices.toInt()
    )
}