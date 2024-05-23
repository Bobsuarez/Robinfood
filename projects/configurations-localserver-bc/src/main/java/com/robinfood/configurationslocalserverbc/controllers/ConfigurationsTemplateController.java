package com.robinfood.configurationslocalserverbc.controllers;

import com.robinfood.configurationslocalserverbc.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.configurationslocalserverbc.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.configurationslocalserverbc.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.configurationslocalserverbc.dtos.templateResponse.PrintingTemplateResponseDTO;
import com.robinfood.configurationslocalserverbc.enums.ApiResponseEnum;
import com.robinfood.configurationslocalserverbc.usecase.getreponseprintingtemplates
        .IGetResponsePrintingTemplatesUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.robinfood.configurationslocalserverbc.configs.constans.APIConstants.CONFIGURATION_GET_TEMPLATE_V1;
import static com.robinfood.configurationslocalserverbc.configs.constans.APIConstants.CONFIGURATION_LOCALSERVER_V1;

@Slf4j
@RestController
@RequestMapping(CONFIGURATION_LOCALSERVER_V1)
public class ConfigurationsTemplateController implements IConfigurationsTemplateController {

    private final IGetResponsePrintingTemplatesUseCase getResponsePrintingTemplatesUseCase;

    public ConfigurationsTemplateController(
            IGetResponsePrintingTemplatesUseCase getResponsePrintingTemplatesUseCase) {
        this.getResponsePrintingTemplatesUseCase = getResponsePrintingTemplatesUseCase;
    }

    @Override
    @GetMapping(CONFIGURATION_GET_TEMPLATE_V1)
    public ResponseEntity<APIResponseDTO<PrintingTemplateResponseDTO>> getConfigurationPosByStoreAndUser(
            @RequestParam(value = "storeId") Long storeId) {

        log.info("ConfigurationsTemplateController() Get Configurations Template by store Id: {}", storeId);

        final PrintingTemplateResponseDTO printingTemplateResponseDTO = getResponsePrintingTemplatesUseCase
                .invoke(storeId);

        AbstractApiResponseBuilderDTO<PrintingTemplateResponseDTO> responseBuilderDTO =
                new OkAbstractApiResponseBuilderDTO<>();

        responseBuilderDTO.build(printingTemplateResponseDTO, ApiResponseEnum
                .RESPONSE_OK_CONFIGURATION_LOCALSERVER_TEMPLATE_BY_STORE);

        log.info("ConfigurationsTemplateController() Get Configurations Templates By StoreId: {} Response: {} "
                , storeId, responseBuilderDTO.getApiResponseDTO());

        return ResponseEntity.ok().body(responseBuilderDTO.getApiResponseDTO());

    }
}
