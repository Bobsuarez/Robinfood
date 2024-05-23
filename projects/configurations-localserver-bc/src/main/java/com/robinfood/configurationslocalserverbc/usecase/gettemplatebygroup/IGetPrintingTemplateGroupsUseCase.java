package com.robinfood.configurationslocalserverbc.usecase.gettemplatebygroup;

import com.robinfood.configurationslocalserverbc.dtos.template.PrintingTemplateGroupsDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IGetPrintingTemplateGroupsUseCase {

    /**
     * Finds a group by id
     *
     * @param groupId group of templates
     * @return Templates of store
     */
    List<PrintingTemplateGroupsDTO> invoke(Long groupId);
}
