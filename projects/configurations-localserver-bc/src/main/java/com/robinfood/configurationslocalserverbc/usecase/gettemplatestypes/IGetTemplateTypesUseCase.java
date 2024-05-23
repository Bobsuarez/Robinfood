package com.robinfood.configurationslocalserverbc.usecase.gettemplatestypes;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateTypesDTO;
import org.springframework.stereotype.Component;

@Component
public interface IGetTemplateTypesUseCase {

    /**
     * Finds a printing template type by id
     *
     * @param printingTemplateTypeId group of store
     * @return Printing Template Type
     */
    PrintingTemplateTypesDTO invoke(Long printingTemplateTypeId);
}
