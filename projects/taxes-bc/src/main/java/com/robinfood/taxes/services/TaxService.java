package com.robinfood.taxes.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.Tax;

import java.util.List;

public interface TaxService {

    List<Tax> list(Long familyId);

    Tax create(Tax tax) throws JsonProcessingException, BusinessRuleException;

    Tax update(Long id, Tax tax) throws JsonProcessingException, BusinessRuleException;

    void delete(Long id) throws JsonProcessingException, BusinessRuleException;

}
