package com.robinfood.configurationslocalserverbc.usecase.gettemplatebystoreid;

import com.robinfood.configurationslocalserverbc.dtos.templateResponse.TemplateDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IGetTemplatesByStoreIdUseCase {

    /**
     * Finds a Treasury Department by a Store id
     *
     * @param storeId Store id
     * @return Treasury Department
     */
    List<TemplateDTO> invoke(Long storeId);
}
