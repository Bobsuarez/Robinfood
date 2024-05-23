package com.robinfood.taxes.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.ExpressionFormulaVariable;

public interface ExpressionFormulaVariableService {

    ExpressionFormulaVariable create(ExpressionFormulaVariable expressionFormulaVariable)
        throws JsonProcessingException, BusinessRuleException;
}
