package com.robinfood.configurationslocalserverbc.usecase.getgroupstore;

import com.robinfood.configurationslocalserverbc.dtos.template.TemplateGroupsStoresDTO;
import org.springframework.stereotype.Component;

@Component
public interface IGetGroupByStoreUseCase {

    /**
     * Finds a Template Groups by a Store id
     *
     * @param storeId Store id
     * @return Template Groups Store
     */
    TemplateGroupsStoresDTO invoke(Long storeId);
}
