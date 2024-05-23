package com.robinfood.configurations.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.exceptions.BusinessRuleException;
import com.robinfood.configurations.models.StoreBrand;


public interface StoreBrandsService {

    int countStoreBrandByBrandIdAndStoreId (Long branId, Long storeId)
            throws JsonProcessingException, BusinessRuleException;

    StoreBrand create(StoreBrand recievedStoreBrand) throws JsonProcessingException, BusinessRuleException;
}
