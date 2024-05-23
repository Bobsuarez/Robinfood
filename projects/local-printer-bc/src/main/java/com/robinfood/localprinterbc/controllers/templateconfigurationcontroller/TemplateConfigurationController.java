package com.robinfood.localprinterbc.controllers.templateconfigurationcontroller;

import com.robinfood.localprinterbc.dtos.apiresponsebuilder.APIResponseDTO;
import com.robinfood.localprinterbc.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.localprinterbc.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.PrintingTemplateDTO;
import com.robinfood.localprinterbc.enums.ApiResponseEnum;
import com.robinfood.localprinterbc.usecases.templateconfiguration.IGetTemplateConfigurationUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


import static com.robinfood.localprinterbc.configs.constants.APIConstants.TEMPLATE;

@RestController
@RequestMapping(TEMPLATE)
@Slf4j
public class TemplateConfigurationController implements ITemplateConfigurationController {


    private final IGetTemplateConfigurationUseCase getTemplateConfigurationUseCase;

    public TemplateConfigurationController(IGetTemplateConfigurationUseCase getTemplateConfigurationUseCase) {
        this.getTemplateConfigurationUseCase = getTemplateConfigurationUseCase;
    }

    @Override
    @GetMapping
    public ResponseEntity<APIResponseDTO<PrintingTemplateDTO>> invoke(@RequestHeader("Authorization") String token,
                                                                      @RequestParam("storeId") Long storeId) {
        log.info("controller: DownloadTemplateController, \n method: invoke, \n param storeId {}", storeId);

        PrintingTemplateDTO printingTemplateDTO = this.getTemplateConfigurationUseCase.invoke(token, storeId);

        AbstractApiResponseBuilderDTO<PrintingTemplateDTO> responseBuilderDTO =
                new OkAbstractApiResponseBuilderDTO<>();
        responseBuilderDTO.build(printingTemplateDTO, ApiResponseEnum.RESPONSE_OK_TEMPLATE_FOUND);

        return ResponseEntity.ok().body(responseBuilderDTO.getApiResponseDTO());
    }
}
