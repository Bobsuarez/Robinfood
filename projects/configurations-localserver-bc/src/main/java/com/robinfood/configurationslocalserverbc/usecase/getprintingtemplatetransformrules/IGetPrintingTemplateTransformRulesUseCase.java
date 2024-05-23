package com.robinfood.configurationslocalserverbc.usecase.getprintingtemplatetransformrules;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateTransformRulesDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IGetPrintingTemplateTransformRulesUseCase {

    /**
     * Finds a ids de transforms rules by printingTemplateId
     *
     * @param printingTemplateId printing template id
     * @return List de rules by Template
     */
    List<PrintingTemplateTransformRulesDTO> invoke(Long printingTemplateId);
}
