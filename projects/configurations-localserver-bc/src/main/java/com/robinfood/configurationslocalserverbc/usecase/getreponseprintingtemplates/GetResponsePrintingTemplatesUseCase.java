package com.robinfood.configurationslocalserverbc.usecase.getreponseprintingtemplates;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateBrandGroupsDTO;
import com.robinfood.configurationslocalserverbc.dtos.templateResponse.PrintingTemplateResponseDTO;
import com.robinfood.configurationslocalserverbc.dtos.templateResponse.TemplateDTO;
import com.robinfood.configurationslocalserverbc.usecase.getprintingtemplatebrandgroups.IGetPrintingTemplateBrandGroupUseCase;
import com.robinfood.configurationslocalserverbc.usecase.gettemplatebystoreid.IGetTemplatesByStoreIdUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GetResponsePrintingTemplatesUseCase implements IGetResponsePrintingTemplatesUseCase {

    private final IGetTemplatesByStoreIdUseCase getTemplatesByStoreIdUseCase;
    private final IGetPrintingTemplateBrandGroupUseCase getPrintingTemplateBrandGroupUseCase;

    public GetResponsePrintingTemplatesUseCase(IGetTemplatesByStoreIdUseCase getTemplatesByStoreIdUseCase,
                                               IGetPrintingTemplateBrandGroupUseCase
                                                       getPrintingTemplateBrandGroupUseCase) {
        this.getTemplatesByStoreIdUseCase = getTemplatesByStoreIdUseCase;
        this.getPrintingTemplateBrandGroupUseCase = getPrintingTemplateBrandGroupUseCase;
    }

    @Override
    public PrintingTemplateResponseDTO invoke(Long storeId) {

        List<TemplateDTO> templateDTO = getTemplatesByStoreIdUseCase.invoke(storeId);

        List<Long> groupIds = templateDTO.stream()
                .map(template -> template.getGroupId()).distinct().collect(Collectors.toList());

        List<PrintingTemplateBrandGroupsDTO> printingTemplateBrandGroupsDTOS = getPrintingTemplateBrandGroupUseCase
                .invoke(groupIds);

        return PrintingTemplateResponseDTO.builder()
                .templates(templateDTO)
                .printingTemplateBrands(printingTemplateBrandGroupsDTOS)
                .build();
    }
}
