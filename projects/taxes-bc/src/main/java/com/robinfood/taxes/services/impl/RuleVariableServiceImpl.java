package com.robinfood.taxes.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.enums.RuleVariableTypeEnum;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.RuleVariable;
import com.robinfood.taxes.repositories.RuleVariableRepository;
import com.robinfood.taxes.repositories.TaxRuleRepository;
import com.robinfood.taxes.services.AuditLogService;
import com.robinfood.taxes.services.RuleVariableService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class RuleVariableServiceImpl implements RuleVariableService {

    private final RuleVariableRepository ruleVariableRepository;
    private final TaxRuleRepository taxRuleRepository;
    private final AuditLogService auditLogService;

    public RuleVariableServiceImpl(
        RuleVariableRepository ruleVariableRepository,
        TaxRuleRepository taxRuleRepository,
        AuditLogService auditLogService) {
        this.ruleVariableRepository = ruleVariableRepository;
        this.taxRuleRepository = taxRuleRepository;
        this.auditLogService = auditLogService;
    }

    @BasicLog
    @Override
    public List<RuleVariable> findAll() {
        return ruleVariableRepository.deletedAtIsNull();
    }

    @BasicLog
    @Override
    public RuleVariable create(@NotNull final RuleVariable ruleVariable)
        throws JsonProcessingException, BusinessRuleException {
        log.trace("Starting process to create rule variable on service with object {}.",
            ruleVariable);

        validateCreateRuleVariable(ruleVariable);

        log.trace("Creating rule variable: {}.", ruleVariable);
        RuleVariable ruleVariableCreated = ruleVariableRepository.save(ruleVariable);
        log.trace("Rule VariableRepository saved successfully: {}.", ruleVariableCreated);

        auditLogService.createAuditLog(ruleVariableCreated);

        log.trace("Process to create rule variable on service ended successfully.");

        return ruleVariableCreated;
    }

    @BasicLog
    @Override
    public void delete(Long id) throws JsonProcessingException, BusinessRuleException {
        log.trace("Starting process on service to delete rule variable with id: {}", id);

        log.trace("Checking if rule variable with id {} exists", id);
        RuleVariable ruleVariable = findById(id);
        log.trace("Rule variable with id {} found. {}", id, ruleVariable);

        log.trace("Checking if rule variable with id {} has tax rule associations", id);
        if(taxRuleRepository.existsByLeftVariableIdOrRightVariableId(id)) {
            BusinessRuleException ex = new BusinessRuleException(
                String.format("Rule variable with id '%s' has tax rule associations", id)
            );
            log.error(ex.getMessage(), ex);
            throw ex;
        }
        log.trace("rule variable with id {} has no tax rule associations", id);

        log.trace("Deleting rule variable with id {}", id);
        ruleVariableRepository.delete(ruleVariable);
        log.trace("Rule variable deleted successfully");

        log.trace("Saving delete rule variable audit log");
        auditLogService.deleteAuditLog(ruleVariable);
        log.trace("Delete audit log saved successfully");

    }

    private RuleVariable findById(@NotNull Long id) {
        return ruleVariableRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException(
                String.format("Rule variable with id: %1$s not found", id)));
    }

    private void validateCreateRuleVariable(RuleVariable ruleVariable) throws BusinessRuleException {

        if (ruleVariable.getPath() == null && ruleVariable.getValue() == null) {
            BusinessRuleException ex =
                new BusinessRuleException("Path or value are mandatory to create a rule variable");
            log.error(ex.getMessage(), ex);
            throw ex;
        }

        if ((ruleVariable.getPath() != null && !ruleVariable.getPath().isBlank())
            && (ruleVariable.getValue() != null && !ruleVariable.getValue().isBlank())) {
            BusinessRuleException ex =
                new BusinessRuleException("Path and value cannot both be with information");
            log.error(ex.getMessage(), ex);
            throw ex;
        }

        boolean existsWithPath = false;
        if (ruleVariable.getPath() != null) {
            existsWithPath =
                ruleVariableRepository.existsByPathAndNameAndDeletedAtIsNull(ruleVariable.getPath(),
                    ruleVariable.getName());
        }

        if (existsWithPath) {
            throw new BusinessRuleException(
                String.format("Rule variable with Path %1$s and Name %2$s already exist.",
                    ruleVariable.getPath(), ruleVariable.getName()));
        }

        boolean isValidType = isValidTypeRuleVariableEnum(ruleVariable.getType());

        if (isValidType) {
            throw new BusinessRuleException(
                String.format("Type Rule Variable Enum: '%1$s' does not valid.",
                    ruleVariable.getType()));
        }

    }

    private static boolean isValidTypeRuleVariableEnum(final String typeRuleVariableEnum) {
        return RuleVariableTypeEnum.toEnum(typeRuleVariableEnum) == null;
    }

}
