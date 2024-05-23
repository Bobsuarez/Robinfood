package com.robinfood.taxes.services.impl;

import static com.robinfood.taxes.constants.CalculatorConstants.EXPRESSION_TYPE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.enums.FormulaVariableTypeEnum;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.FormulaVariable;
import com.robinfood.taxes.repositories.ExpressionFormulaVariableRepository;
import com.robinfood.taxes.repositories.ExpressionRepository;
import com.robinfood.taxes.repositories.FormulaVariableRepository;
import com.robinfood.taxes.services.AuditLogService;
import com.robinfood.taxes.services.FormulaVariableService;
import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class FormulaVariableServiceImpl implements FormulaVariableService {

    private final FormulaVariableRepository formulaVariableRepository;

    private final ExpressionRepository expressionRepository;

    private final ExpressionFormulaVariableRepository expressionFormulaVariableRepository;

    private final AuditLogService auditLogService;

    public FormulaVariableServiceImpl(
        @NotNull final FormulaVariableRepository formulaVariableRepository,
        @NotNull final ExpressionRepository expressionRepository,
        @NotNull final ExpressionFormulaVariableRepository expressionFormulaVariableRepository,
        @NotNull final AuditLogService auditLogService) {
        this.formulaVariableRepository = formulaVariableRepository;
        this.expressionRepository = expressionRepository;
        this.auditLogService = auditLogService;
        this.expressionFormulaVariableRepository = expressionFormulaVariableRepository;
    }

    @BasicLog
    @Override
    @Transactional(readOnly = true)
    public Page<FormulaVariable> findAll(Pageable pageable) {
        log.trace("Getting formula variable list on service");
        return formulaVariableRepository.deletedAtIsNull(pageable);
    }

    @BasicLog
    @Override
    @Transactional
    public FormulaVariable create(FormulaVariable formulaVariable)
        throws JsonProcessingException, BusinessRuleException {

        log.trace("Starting process to create formula variable on service with object {}.",
            formulaVariable);

        log.trace("Checking if type {} is valid", formulaVariable.getType());
        if (FormulaVariableTypeEnum.toEnum(formulaVariable.getType()) == null) {
            throw new BusinessRuleException(
                String.format("Type %s not valid", formulaVariable.getType()));
        }

        if (formulaVariable.getType().equals(EXPRESSION_TYPE)) {
            validateValueInExpressions(formulaVariable.getValue());
        }

        log.trace("Checking if name {} is valid", formulaVariable.getName());
        if (nameExists(formulaVariable.getName())) {
            throw new BusinessRuleException(
                String.format("Name %s already exists", formulaVariable.getName()));
        }
        log.trace("Name is valid");

        log.trace("Saving formula variable on DB. {}", formulaVariable);
        FormulaVariable createdFormulaVariable = formulaVariableRepository.save(formulaVariable);
        log.trace("Formula variable created successfully on DB. {}", createdFormulaVariable);

        log.trace("Saving audit log for formula variable creation");
        auditLogService.createAuditLog(createdFormulaVariable);
        log.trace("Audit log for formula variable creation saved successfully");

        return createdFormulaVariable;
    }

    @BasicLog
    @Override
    public void delete(@NotNull final Long id)
        throws BusinessRuleException, JsonProcessingException {
        log.trace("Starting process on service to delete formula variable with ID {}.", id);

        log.trace("Checking if formula variable with Id {} exist.", id);
        FormulaVariable formulaVariable = findById(id);
        log.trace("Formula Variable with ID {} found: {}", id, formulaVariable);

        log.trace("Checking if formula variable with id {} is active on taxes", id);

        if (expressionFormulaVariableRepository.existsByFormulaVariableIdAndDeletedAtIsNull(id)) {
            throw new BusinessRuleException(String.format(
                "Formula Variable with id %1$s is related to one or more "
                    + "Expression Formula Variable.", id));
        }

        log.trace("Formula Variable with ID {} is not associate to Expression Formula Variable.",
            id);

        log.trace("Deleting Formula Variable with ID {}.", formulaVariable);
        formulaVariableRepository.delete(formulaVariable);
        log.trace("Formula Variable with ID {} deleted successfully.", id);

        log.trace("Generating delete auditLog.");
        auditLogService.deleteAuditLog(formulaVariable);
        log.trace("AuditLog generated successfully.");

        log.trace("Process to delete formula variable ended successfully.");
    }

    private boolean expressionExists(Long id) {
        return expressionRepository.existsByIdAndDeletedAtIsNull(id);
    }

    private boolean nameExists(String name) {
        return formulaVariableRepository.existsByNameAndDeletedAtIsNull(name);
    }

    private void validateValueInExpressions(String value) throws BusinessRuleException {
        log.trace("Checking if value {} of type expression is valid", value);
        try {
            Long id = Long.valueOf(value);
            if (!expressionExists(id)) {
                throw new EntityNotFoundException(
                    String.format("Expression with id: %s not found", id));
            }
            log.trace("Value of type expression is valid");
        } catch (NumberFormatException ex){
            throw new BusinessRuleException("Value is not valid for evaluate expression type");
        }
    }

    private FormulaVariable findById(Long id) {
        return formulaVariableRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format("Formula Variable with ID %1$s not found.", id)));
    }

}
