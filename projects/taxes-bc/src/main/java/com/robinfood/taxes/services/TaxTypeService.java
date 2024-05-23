package com.robinfood.taxes.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.TaxType;
import java.util.List;

public interface TaxTypeService {

    List<TaxType> list(Long countryId, Integer status);

    TaxType create(TaxType tax) throws JsonProcessingException, BusinessRuleException;

    TaxType update(Long id, TaxType tax) throws JsonProcessingException, BusinessRuleException;

    void delete(Long id) throws JsonProcessingException, BusinessRuleException;

}
