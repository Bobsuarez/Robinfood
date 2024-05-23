package com.robinfood.orderorlocalserver.controllers;

import com.robinfood.orderorlocalserver.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.orderorlocalserver.dtos.printingtemplate.PrintingTemplateDTO;
import org.springframework.http.ResponseEntity;

public interface IPrintingTemplateController {

    ResponseEntity<APIResponseDTO<PrintingTemplateDTO>> getPrintingTemplates(Long storeId);
}
