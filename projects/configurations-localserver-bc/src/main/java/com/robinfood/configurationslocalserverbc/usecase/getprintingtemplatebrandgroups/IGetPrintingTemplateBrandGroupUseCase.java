package com.robinfood.configurationslocalserverbc.usecase.getprintingtemplatebrandgroups;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateBrandGroupsDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IGetPrintingTemplateBrandGroupUseCase {
    /**
     * Finds a template by brand and group
     *
     * @param groupIds group of store
     * @return Printing Template Brand by Groups
     */
    List<PrintingTemplateBrandGroupsDTO> invoke(List<Long> groupIds);
}
