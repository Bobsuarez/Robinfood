package com.robinfood.orderorlocalserver.controllers;

import com.robinfood.orderorlocalserver.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.orderorlocalserver.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.orderorlocalserver.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.orderorlocalserver.dtos.printingtemplate.PrintingTemplateDTO;
import com.robinfood.orderorlocalserver.enums.ApiResponseEnum;
import com.robinfood.orderorlocalserver.usecases.getprintingtemplate.IGetPrintingTemplateUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static com.robinfood.orderorlocalserver.constants.ApiConstants.API_GET_PRINTING_TEMPLATES;
import static com.robinfood.orderorlocalserver.constants.ApiConstants.API_PRINTING_TEMPLATES_V1;

@RestController
@Slf4j
@RequestMapping(API_PRINTING_TEMPLATES_V1)
public class PrintingTemplateController implements IPrintingTemplateController {

    private final IGetPrintingTemplateUseCase iGetPrintingTemplateUseCase;

    public PrintingTemplateController(
            IGetPrintingTemplateUseCase iGetPrintingTemplateUseCase
    ){
        this.iGetPrintingTemplateUseCase = iGetPrintingTemplateUseCase;
    }

    @Override
    @GetMapping(API_GET_PRINTING_TEMPLATES)
    public ResponseEntity<APIResponseDTO<PrintingTemplateDTO>> getPrintingTemplates(
            @RequestParam(value = "storeId") Long storeId
    ) {

        log.info("Init request get printing template by store id: {}", storeId);

        PrintingTemplateDTO printingTemplateDTOResult =
                iGetPrintingTemplateUseCase.invoke(storeId);

        log.info("This is the response from use case {}", printingTemplateDTOResult);

        AbstractApiResponseBuilderDTO<PrintingTemplateDTO> responseBuilderDTO =
                new OkAbstractApiResponseBuilderDTO<>();

        responseBuilderDTO.build(
                printingTemplateDTOResult,
                ApiResponseEnum.RESPONSE_OK_TEMPLATES_BY_STORE
        );

        log.info("This is the response from printing template controller {}",
                responseBuilderDTO.getApiResponseDTO());

        return ResponseEntity.ok().body(responseBuilderDTO.getApiResponseDTO());
    }
}
