package com.robinfood.repository.mocks.customerinvoice

import com.robinfood.core.entities.customerinvoice.ServicesInvoiceEntity

class ServicesInvoiceEntityMock {

    val servicesInvoiceEntityMock = listOf(
        ServicesInvoiceEntity(
            "$",
            DiscountInvoiceEntityMock().discountInvoiceEntityMock,
            "Valor Servicios",
            SubTotalInvoiceEntityMock().subTotalInvoiceEntityMock,
            TaxInvoiceEntityMock().taxInvoiceEntityMock,
            TotalInvoiceEntityMock().totalInvoiceEntityMock
        )
    )
}