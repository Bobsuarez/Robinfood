package com.robinfood.localprinterbc.controllers.printinvoicecontroller;

import com.robinfood.localprinterbc.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.localprinterbc.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.localprinterbc.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.localprinterbc.dtos.invoice.InvoiceDetailDTO;
import com.robinfood.localprinterbc.enums.ApiResponseEnum;
import com.robinfood.localprinterbc.usecases.printinvoicesusecase.IExecutePrintInvoiceUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

import static com.robinfood.localprinterbc.configs.constants.APIConstants.INVOICE;
import static com.robinfood.localprinterbc.configs.constants.APIConstants.PRINT_INVOICES;

@RestController
@RequestMapping(INVOICE)
@Slf4j
public class PrintInvoiceController implements IPrintInvoiceController {

    private final IExecutePrintInvoiceUseCase executePrintInvoiceUseCase;

    public PrintInvoiceController(IExecutePrintInvoiceUseCase executePrintInvoiceUseCase) {
        this.executePrintInvoiceUseCase = executePrintInvoiceUseCase;
    }

    @Override
    @PostMapping(PRINT_INVOICES)
    public ResponseEntity<APIResponseDTO<Map<String, Object>>> invoke(String token, InvoiceDetailDTO invoiceDetailDTO)
            throws IOException {
        log.info("Controller: {}, Service: {} " , this.getClass().getName(), INVOICE+PRINT_INVOICES);
        log.info("invoiceDetailDTO {} ", invoiceDetailDTO);

        Map<String, Object> response = executePrintInvoiceUseCase.printInvoice(token, invoiceDetailDTO);

        AbstractApiResponseBuilderDTO<Map<String, Object>> responseData =
                new OkAbstractApiResponseBuilderDTO<>();
        responseData.build(response, ApiResponseEnum.RESPONSE_OK_PRINT_INVOICES);

        return ResponseEntity.ok().body(responseData.getApiResponseDTO());
    }
}
