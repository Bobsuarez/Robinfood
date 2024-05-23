package com.robinfood.taxes.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.ExpressionFormulaVariable;
import com.robinfood.taxes.repositories.ExpressionFormulaVariableRepository;
import com.robinfood.taxes.repositories.ExpressionRepository;
import com.robinfood.taxes.repositories.FormulaVariableRepository;
import com.robinfood.taxes.services.AuditLogService;
import com.robinfood.taxes.services.ExpressionFormulaVariableService;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class ExpressionFormulaVariableServiceImpl implements ExpressionFormulaVariableService {

    private final ExpressionFormulaVariableRepository expressionFormulaVariableRepository;
    private final ExpressionRepository expressionRepository;
    private final FormulaVariableRepository formulaVariableRepository;
    private final AuditLogService auditLogService;


    public ExpressionFormulaVariableServiceImpl(
        ExpressionFormulaVariableRepository expressionFormulaVariableRepository,
        ExpressionRepository expressionRepository,
        FormulaVariableRepository formulaVariableRepository,
        AuditLogService auditLogService) {
        this.expressionFormulaVariableRepository = expressionFormulaVariableRepository;
        this.expressionRepository = expressionRepository;
        this.formulaVariableRepository = formulaVariableRepository;
        this.auditLogService = auditLogService;
    }

    @BasicLog
    @Override
    public ExpressionFormulaVariable create(ExpressionFormulaVariable expressionFormulaVariable)
        throws JsonProcessingException, BusinessRuleException {
        log.trace(
            "Starting process in service to create expression formula variable with object: {}.",
            expressionFormulaVariable);

        log.trace("Checking if expression with id {} exists.",
            expressionFormulaVariable.getExpression().getId());
        if (!expressionRepository
            .existsByIdAndDeletedAtIsNull(expressionFormulaVariable.getExpression().getId())) {
            throw new EntityNotFoundException(
                String.format("Entity Expression with Id %s not found",
                    expressionFormulaVariable.getExpression().getId()));
        }
        log.trace("Expression is valid.");

        log.trace("Checking if the formula variable with id {} exists.",
            expressionFormulaVariable.getFormulaVariable().getId());
        if (!formulaVariableRepository
            .existsByIdAndDeletedAtIsNull(expressionFormulaVariable.getFormulaVariable().getId())) {
            throw new EntityNotFoundException(
                String.format("Entity Formula Variable with id %s not found",
                    expressionFormulaVariable.getFormulaVariable().getId()));
        }
        log.trace("Formula variable is valid.");

        log.trace(
            "Checking if the expression with id {},  and formula variable with id {} is valid.",
            expressionFormulaVariable.getExpression().getId(),
            expressionFormulaVariable.getFormulaVariable().getId());
        if (expressionFormulaVariableRepository
            .existsByExpressionIdAndFormulaVariableIdAndDeletedAtIsNull(
                expressionFormulaVariable.getExpression().getId(),
                expressionFormulaVariable.getFormulaVariable().getId())) {
            throw new BusinessRuleException(
                String.format("Expression id %s and formula variable id %s already exists",
                    expressionFormulaVariable.getExpression().getId(),
                    expressionFormulaVariable.getFormulaVariable().getId()));
        }
        log.trace("Expression and Formula variable is valid.");

        log.trace("Saving expression formula variable: {}.", expressionFormulaVariable);
        ExpressionFormulaVariable expressionFormulaVariableCreated = expressionFormulaVariableRepository
            .save(expressionFormulaVariable);
        log.trace("Expression formula variable saved successfully: {}.",
            expressionFormulaVariableCreated);

        auditLogService.createAuditLog(expressionFormulaVariableCreated);
        log.trace("Process to create expression formula variable on service ended successfully.");

        return expressionFormulaVariableCreated;
    }


}
