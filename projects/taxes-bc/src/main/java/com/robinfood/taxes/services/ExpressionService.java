package com.robinfood.taxes.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.Expression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExpressionService {

    Expression create(Expression expression) throws JsonProcessingException;

    void delete(Long id) throws JsonProcessingException, BusinessRuleException;

    Page<Expression> findAll(boolean onlyActive, Pageable pageable);
}
