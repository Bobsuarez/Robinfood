package com.robinfood.configurationslocalserverbc.usecase.getprintingtemplate;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplatesDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IGetPrintingTemplateUseCase {

    /**
     * Finds a group by id
     *
     * @param printTemplateIds group of templates
     * @return Templates of store
     */
    List<PrintingTemplatesDTO> invoke(List<Long> printTemplateIds);
}
