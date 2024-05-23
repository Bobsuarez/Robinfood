package com.robinfood.app.mocks.customerinvoice

import com.robinfood.core.dtos.customerinvoice.PrintFormatDTO

class PrintFormatDTOMock {

    val printFormatDTOMock = PrintFormatDTO(
        CompanyInvoiceDTOMock().companyInvoiceDTO,
        OrderInvoiceDTOMock().orderInvoiceDTOMock,
        PrintDTOMock().printDTOMock
    )

    val printFormatDTOIsExternalFalseMock = PrintFormatDTO(
        CompanyInvoiceDTOMock().companyInvoiceDTO,
        OrderInvoiceDTOMock().orderInvoiceDTOIsExternalDeliveryFalseMock,
        PrintDTOMock().printDTOMock
    )
}