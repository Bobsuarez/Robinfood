package com.robinfood.taxes.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.RuleVariable;
import java.util.List;

public interface RuleVariableService {

    RuleVariable create(RuleVariable ruleVariable) throws JsonProcessingException, BusinessRuleException;

    List<RuleVariable> findAll();

    void delete(Long id) throws JsonProcessingException, BusinessRuleException;

}
