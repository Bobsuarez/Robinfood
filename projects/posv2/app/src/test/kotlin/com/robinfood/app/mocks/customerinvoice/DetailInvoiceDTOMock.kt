package com.robinfood.app.mocks.customerinvoice

import com.robinfood.core.dtos.customerinvoice.DetailInvoiceDTO

class DetailInvoiceDTOMock {

    val detailInvoiceDTOMock = DetailInvoiceDTO(
        "$",
        DiscountInvoiceDTOMock().discountInvoiceDTOMock,
        SubTotalInvoiceDTOMock().subTotalInvoiceDTOMock,
        TaxInvoiceDTOMock().taxInvoiceDTOMock,
        "Valor productos",
        TotalInvoiceDTOMock().totalInvoiceDTOMock,
        TypeTaxInvoiceDTOMock().typeTaxInvoiceDTOMock
    )
}