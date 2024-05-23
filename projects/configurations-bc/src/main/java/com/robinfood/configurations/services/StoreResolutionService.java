package com.robinfood.configurations.services;

import com.robinfood.configurations.dto.v1.ActivateOrDeactivateDTO;
import com.robinfood.configurations.dto.v1.ResponseResolutionsWithPosDTO;
import com.robinfood.configurations.dto.v1.StoreResolutionDTO;
import com.robinfood.configurations.exceptions.BusinessRuleException;

import java.util.List;

public interface StoreResolutionService {

    List<ResponseResolutionsWithPosDTO> create(List<StoreResolutionDTO> storeResolutionDTOS);

    String update(StoreResolutionDTO storeResolutionDTOS, Long resolutionId)
            throws BusinessRuleException;
    Boolean isActivateOrDeactivateByResolutionId(ActivateOrDeactivateDTO activateOrDeactivateDTO, Long resolutionId);
}
