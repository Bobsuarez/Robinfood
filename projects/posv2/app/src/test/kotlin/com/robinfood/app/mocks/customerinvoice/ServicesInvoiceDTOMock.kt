package com.robinfood.app.mocks.customerinvoice

import com.robinfood.core.dtos.customerinvoice.ServicesInvoiceDTO

class ServicesInvoiceDTOMock {

    val servicesInvoiceDTOMock = listOf(
        ServicesInvoiceDTO(
            "$",
            "Valor Servicios",
            DiscountInvoiceDTOMock().discountInvoiceDTOMock,
            SubTotalInvoiceDTOMock().subTotalInvoiceDTOMock,
            TaxInvoiceDTOMock().taxInvoiceDTOMock,
            TotalInvoiceDTOMock().totalInvoiceDTOMock
        )
    )
}