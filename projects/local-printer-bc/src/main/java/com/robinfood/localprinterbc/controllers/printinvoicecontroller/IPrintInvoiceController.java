package com.robinfood.localprinterbc.controllers.printinvoicecontroller;

import com.robinfood.localprinterbc.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.localprinterbc.dtos.invoice.InvoiceDetailDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.IOException;
import java.util.Map;

public interface IPrintInvoiceController {
    ResponseEntity<APIResponseDTO<Map<String, Object>>> invoke(@RequestHeader("Authorization") String token,
                                                               @RequestBody InvoiceDetailDTO invoiceDetailDTO)
            throws IOException;
}
