package com.robinfood.taxes.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.TaxRule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaxRuleService {

    Page<TaxRule> list(Long taxId, Integer status, Pageable pageable);

    TaxRule create(TaxRule taxRule) throws BusinessRuleException, JsonProcessingException;

    TaxRule update(Long id, TaxRule taxRule) throws BusinessRuleException, JsonProcessingException;
    
    void delete(Long id) throws JsonProcessingException, BusinessRuleException;

}
