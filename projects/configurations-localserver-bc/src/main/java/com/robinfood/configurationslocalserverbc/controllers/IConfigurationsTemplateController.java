package com.robinfood.configurationslocalserverbc.controllers;

import com.robinfood.configurationslocalserverbc.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.configurationslocalserverbc.dtos.templateResponse.PrintingTemplateResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IConfigurationsTemplateController {

    ResponseEntity<APIResponseDTO<PrintingTemplateResponseDTO>> getConfigurationPosByStoreAndUser(Long storeId);

}
