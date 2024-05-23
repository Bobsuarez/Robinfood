package com.robinfood.taxes.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.FormulaVariable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FormulaVariableService {

    Page<FormulaVariable> findAll(Pageable pageable);

    FormulaVariable create(FormulaVariable formulaVariable)
        throws JsonProcessingException, BusinessRuleException;

    void delete(Long id) throws BusinessRuleException, JsonProcessingException;
}
