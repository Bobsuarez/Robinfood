package com.robinfood.configurationslocalserverbc.usecase.getresponserules;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateTransformRulesDTO;
import com.robinfood.configurationslocalserverbc.dtos.template.TransformRulesDTO;
import com.robinfood.configurationslocalserverbc.dtos.templateResponse.TransformRulesResponseDTO;
import com.robinfood.configurationslocalserverbc.mappers.ITransformRulesMapper;
import com.robinfood.configurationslocalserverbc.usecase.getprintingtemplatetransformrules
        .IGetPrintingTemplateTransformRulesUseCase;
import com.robinfood.configurationslocalserverbc.usecase.gettransformrules.IGetTransformRulesUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GetResponseRulesUseCase implements IGetResponseRulesUseCase {

    private final IGetPrintingTemplateTransformRulesUseCase getPrintingTemplateTransformRulesUseCase;
    private final IGetTransformRulesUseCase getTransformRulesUseCase;
    private final ITransformRulesMapper transformRulesMapper;

    public GetResponseRulesUseCase(IGetPrintingTemplateTransformRulesUseCase
                                           getPrintingTemplateTransformRulesUseCase,
                                   IGetTransformRulesUseCase getTransformRulesUseCase,
                                   ITransformRulesMapper transformRulesMapper
    ) {
        this.getPrintingTemplateTransformRulesUseCase = getPrintingTemplateTransformRulesUseCase;
        this.getTransformRulesUseCase = getTransformRulesUseCase;
        this.transformRulesMapper = transformRulesMapper;
    }

    @Override
    public List<TransformRulesResponseDTO> invoke(Long printingTemplateId) {

        List<PrintingTemplateTransformRulesDTO>
                printingTemplateTransformRulesEntities = getPrintingTemplateTransformRulesUseCase
                .invoke(printingTemplateId);

        List<Long> printingTransformRulesIds = printingTemplateTransformRulesEntities.stream()
                .map(template -> template.getTransformRuleId()).collect(Collectors.toList());

        List<TransformRulesDTO> transformRulesDTOS = getTransformRulesUseCase.invoke(printingTransformRulesIds);

        return transformRulesMapper.listTransformRulesDTOToListTransformRulesResponseDTO(transformRulesDTOS);
    }
}
