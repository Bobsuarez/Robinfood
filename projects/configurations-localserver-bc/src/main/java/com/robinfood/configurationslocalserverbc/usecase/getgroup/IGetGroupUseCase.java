package com.robinfood.configurationslocalserverbc.usecase.getgroup;

import com.robinfood.configurationslocalserverbc.dtos.template.TemplateGroupsDTO;
import org.springframework.stereotype.Component;

@Component
public interface IGetGroupUseCase {

    /**
     * Finds a group by id
     *
     * @param groupId group of store
     *
     * @return Group of store
     */
    TemplateGroupsDTO invoke(Long groupId);
}
