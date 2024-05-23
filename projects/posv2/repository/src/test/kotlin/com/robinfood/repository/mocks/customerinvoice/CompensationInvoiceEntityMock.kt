package com.robinfood.repository.mocks.customerinvoice

import com.robinfood.core.entities.customerinvoice.CompensationInvoiceEntity

class CompensationInvoiceEntityMock {

    val compensationInvoiceEntityMock = CompensationInvoiceEntity(
        "Compensación por Co2",
        "El aporte de Co2 de esta cuenta está destinado a la compensación a través de bonos de carbono.",
        "$",
        "Responsabilidad social",
        "0,00"
    )
}