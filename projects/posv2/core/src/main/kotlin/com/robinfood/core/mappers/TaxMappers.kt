package com.robinfood.core.mappers

import com.robinfood.core.dtos.transactionrequest.TaxDTO
import com.robinfood.core.entities.transactionrequest.TaxEntity

fun TaxDTO.toTaxEntity(): TaxEntity {
    return TaxEntity(
        dicTaxId = dicTaxId,
        familyTaxTypeId = familyTaxTypeId,
        taxPrice = taxPrice,
        taxValue = taxValue
    )
}