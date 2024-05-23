package com.robinfood.localprinterbc.controllers.templateconfigurationcontroller;

import com.robinfood.localprinterbc.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.PrintingTemplateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

public interface ITemplateConfigurationController {
    ResponseEntity<APIResponseDTO<PrintingTemplateDTO>>invoke(@RequestHeader("Authorization") String token,
                                                              @RequestParam("storeId") Long storeId);
}
