package com.robinfood.taxes.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.Family;
import java.util.List;

public interface FamilyService {

    List<Family> list(Long countryId, Long typeId, Integer status);

    Family create(Family family) throws JsonProcessingException, BusinessRuleException;

    Family update(Long id, Family family)
        throws JsonProcessingException, BusinessRuleException;
    
    void delete(Long id) throws JsonProcessingException, BusinessRuleException;

}
