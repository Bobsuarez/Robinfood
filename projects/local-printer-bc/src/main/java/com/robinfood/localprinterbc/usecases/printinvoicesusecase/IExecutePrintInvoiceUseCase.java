package com.robinfood.localprinterbc.usecases.printinvoicesusecase;


import com.robinfood.localprinterbc.dtos.invoice.InvoiceDetailDTO;

import java.io.IOException;
import java.util.Map;

public interface IExecutePrintInvoiceUseCase {
    Map<String, Object> printInvoice(String token, InvoiceDetailDTO invoiceDetailDTO)throws IOException;
}
