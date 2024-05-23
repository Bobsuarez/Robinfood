package com.robinfood.taxes.services.impl;

import static com.robinfood.taxes.constants.GeneralConstants.ACTIVE_STATUS;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.Expression;
import com.robinfood.taxes.repositories.ExpressionFormulaVariableRepository;
import com.robinfood.taxes.repositories.ExpressionRepository;
import com.robinfood.taxes.repositories.TaxRepository;
import com.robinfood.taxes.services.AuditLogService;
import com.robinfood.taxes.services.ExpressionService;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class ExpressionServiceImpl implements ExpressionService {

    private final ExpressionRepository expressionRepository;
    private final TaxRepository taxRepository;
    private final ExpressionFormulaVariableRepository expressionFormulaVariableRepository;
    private final AuditLogService auditLogService;

    public ExpressionServiceImpl(final ExpressionRepository expressionRepository,
        TaxRepository taxRepository,
        ExpressionFormulaVariableRepository expressionFormulaVariableRepository,
        final AuditLogService auditLogService) {
        this.expressionRepository = expressionRepository;
        this.taxRepository = taxRepository;
        this.expressionFormulaVariableRepository = expressionFormulaVariableRepository;
        this.auditLogService = auditLogService;
    }

    @BasicLog
    @Override
    public Expression create(Expression expression) throws JsonProcessingException {
        log.trace("Starting process in service to create expression with object: {}.", expression);
        Expression createdExpression = save(expression);
        auditLogService.createAuditLog(createdExpression);
        log.trace("Process to create expression on service ended successfully.");
        return createdExpression;
    }

    @BasicLog
    @Override
    public void delete(Long id) throws JsonProcessingException, BusinessRuleException {

        if (taxRepository.existsByExpressionIdAndDeletedAtIsNull(id)) {
            throw new BusinessRuleException(
                String
                    .format("Cannot delete expression with ID %s. It is associated to a tax", id));
        }

        if (expressionFormulaVariableRepository.existsByExpressionIdAndDeletedAtIsNull(id)) {
            throw new BusinessRuleException(
                String.format(
                    "Cannot delete expression with ID %s. It is associated to a formula variable",
                    id));
        }

        Expression expression = expressionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format("Expression with ID %s not found", id)));

        auditLogService.deleteAuditLog(expression);
        expressionRepository.deleteById(id);
    }

    @BasicLog
    @Override
    public Page<Expression> findAll(boolean onlyActive, Pageable pageable) {
        Page<Expression> expressionPage;
        if (onlyActive) {
            expressionPage = expressionRepository.findAllByStatusAndDeletedAtIsNull(
                ACTIVE_STATUS, pageable);
        } else {
            expressionPage = expressionRepository.findAll(pageable);
        }

        return expressionPage;
    }

    private Expression save(Expression expression) {
        log.trace("Saving expression: {}.", expression);
        Expression expressionCreated = expressionRepository.save(expression);
        log.trace("Expression saved successfully: {}.", expressionCreated);
        return expressionCreated;
    }

}
