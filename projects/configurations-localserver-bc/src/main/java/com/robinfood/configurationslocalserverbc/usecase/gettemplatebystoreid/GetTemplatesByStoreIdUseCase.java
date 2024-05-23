package com.robinfood.configurationslocalserverbc.usecase.gettemplatebystoreid;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateGroupsDTO;
import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplatesDTO;
import com.robinfood.configurationslocalserverbc.dtos.template.TemplateGroupsDTO;
import com.robinfood.configurationslocalserverbc.dtos.template.TemplateGroupsStoresDTO;
import com.robinfood.configurationslocalserverbc.dtos.templateResponse.TemplateDTO;
import com.robinfood.configurationslocalserverbc.usecase.getgroup.IGetGroupUseCase;
import com.robinfood.configurationslocalserverbc.usecase.getgroupstore.IGetGroupByStoreUseCase;
import com.robinfood.configurationslocalserverbc.usecase.getprintingtemplate.IGetPrintingTemplateUseCase;
import com.robinfood.configurationslocalserverbc.usecase.getresponserules.IGetResponseRulesUseCase;
import com.robinfood.configurationslocalserverbc.usecase.gettemplatebygroup.IGetPrintingTemplateGroupsUseCase;
import com.robinfood.configurationslocalserverbc.usecase.gettemplatestypes.IGetTemplateTypesUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GetTemplatesByStoreIdUseCase implements IGetTemplatesByStoreIdUseCase {

    private final IGetGroupByStoreUseCase getGroupByStoreUseCase;
    private final IGetGroupUseCase getGroupUseCase;
    private final IGetPrintingTemplateGroupsUseCase getPrintingTemplateGroupsUseCase;
    private final IGetPrintingTemplateUseCase getPrintingTemplateUseCase;
    private final IGetResponseRulesUseCase getResponseRulesUseCase;
    private final IGetTemplateTypesUseCase getTemplateTypesUseCase;


    public GetTemplatesByStoreIdUseCase(IGetGroupByStoreUseCase getGroupByStoreUseCase,
                                        IGetGroupUseCase getGroupUseCase,
                                        IGetPrintingTemplateGroupsUseCase getPrintingTemplateGroupsUseCase,
                                        IGetPrintingTemplateUseCase getPrintingTemplateUseCase,
                                        IGetResponseRulesUseCase getResponseRulesUseCase,
                                        IGetTemplateTypesUseCase getTemplateTypesUseCase
    ) {
        this.getGroupByStoreUseCase = getGroupByStoreUseCase;
        this.getGroupUseCase = getGroupUseCase;
        this.getPrintingTemplateGroupsUseCase = getPrintingTemplateGroupsUseCase;
        this.getPrintingTemplateUseCase = getPrintingTemplateUseCase;
        this.getResponseRulesUseCase = getResponseRulesUseCase;
        this.getTemplateTypesUseCase = getTemplateTypesUseCase;
    }

    @Override
    public List<TemplateDTO> invoke(Long storeId) {

        TemplateGroupsStoresDTO templateGroupsStoresDTO = getGroupByStoreUseCase.invoke(storeId);

        Long groupId = templateGroupsStoresDTO.getGroupId();

        TemplateGroupsDTO templateGroupsDTO = getGroupUseCase.invoke(groupId);

        List<PrintingTemplateGroupsDTO> printingTemplateGroupsDTOS = getPrintingTemplateGroupsUseCase.invoke(groupId);

        List<Long> templateIds = printingTemplateGroupsDTOS.stream()
                .map(template -> template.getPrintingTemplateId()).collect(Collectors.toList());

        List<PrintingTemplatesDTO> printingTemplatesDTOS = getPrintingTemplateUseCase.invoke(templateIds);

        return printingTemplatesDTOS.stream()
                .map(printingTemplatesDTO ->
                        TemplateDTO.builder()
                                .groupId(templateGroupsDTO.getId())
                                .groupName(templateGroupsDTO.getDescription())
                                .templateId(printingTemplatesDTO.getId())
                                .templateString(printingTemplatesDTO.getContent())
                                .rules(getResponseRulesUseCase.invoke(printingTemplatesDTO.getId()))
                                .templateType(getTemplateTypesUseCase
                                        .invoke(printingTemplatesDTO.getPrintingTemplateTypeId()))
                                .build()
                ).collect(Collectors.toList());

    }
}
