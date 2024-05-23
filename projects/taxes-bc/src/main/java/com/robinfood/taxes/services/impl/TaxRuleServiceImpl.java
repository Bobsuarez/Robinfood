package com.robinfood.taxes.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.RuleType;
import com.robinfood.taxes.models.RuleVariable;
import com.robinfood.taxes.models.Tax;
import com.robinfood.taxes.models.TaxRule;
import com.robinfood.taxes.repositories.RuleTypeRepository;
import com.robinfood.taxes.repositories.RuleVariableRepository;
import com.robinfood.taxes.repositories.TaxRepository;
import com.robinfood.taxes.repositories.TaxRuleRepository;
import com.robinfood.taxes.services.AuditLogService;
import com.robinfood.taxes.services.TaxRuleService;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class TaxRuleServiceImpl implements TaxRuleService {

    private final TaxRuleRepository taxRuleRepository;

    private final TaxRepository taxRepository;

    private final RuleVariableRepository ruleVariableRepository;

    private final RuleTypeRepository ruleTypeRepository;

    private final AuditLogService auditLogService;

    public TaxRuleServiceImpl(@NotNull final TaxRuleRepository taxRuleRepository,
        @NotNull final TaxRepository taxRepository,
        @NotNull final RuleVariableRepository ruleVariableRepository,
        @NotNull final RuleTypeRepository ruleTypeRepository,
        @NotNull final AuditLogService auditLogService) {
        this.taxRuleRepository = taxRuleRepository;
        this.taxRepository = taxRepository;
        this.ruleVariableRepository = ruleVariableRepository;
        this.ruleTypeRepository = ruleTypeRepository;
        this.auditLogService = auditLogService;
    }

    @BasicLog
    @Override
    public Page<TaxRule> list(Long taxId, Integer status, Pageable pageable) {
        log.trace("Starting process on list tax rules service with tax id {} and status {}",
            taxId, status);
        return taxRuleRepository.findByTaxIdAndStatusAndDeletedAtIsNull(taxId, status, pageable);
    }

    @BasicLog
    @Override
    public TaxRule create(TaxRule taxRule) throws BusinessRuleException, JsonProcessingException {
        log.trace("Starting process in service to create tax rule with object: {}.", taxRule);
        performValidations(taxRule);
        TaxRule taxRuleCreated = save(taxRule);
        auditLogService.createAuditLog(taxRuleCreated);
        log.trace("Process to create tax rule on service ended successfully.");
        return taxRuleCreated;
    }

    @BasicLog
    @Override
    public TaxRule update(Long id, TaxRule taxRuleChanges)
        throws BusinessRuleException, JsonProcessingException {
        log.trace("Starting process in service to update tax rule with object: {}.",
            taxRuleChanges);

        log.trace("Checking if tax rule with id {} exists", id);
        TaxRule taxRule = findTaxRuleById(id).toBuilder().build();
        log.trace("Tax rule found. {}", taxRule);

        TaxRule beforeUpdate = taxRule.toBuilder().build();

        log.trace("Updating tax rule fields. {}", taxRuleChanges);
        updateTaxRuleFields(taxRule, taxRuleChanges);
        log.trace("Fields updated successfully on object. {}", taxRule);

        performValidations(taxRule);

        log.trace("Updating tax rule on DB");
        TaxRule updatedTaxRule = save(taxRule);
        log.trace("Tax rule updated successfully");

        log.trace("Saving update tax rule audit log");
        auditLogService.updateAuditLog(beforeUpdate, updatedTaxRule);
        log.trace("Update audit log saved successfully");

        return updatedTaxRule;
    }

    @BasicLog
    @Override
    public void delete(Long id) throws JsonProcessingException, BusinessRuleException {
        log.trace("Starting process on service delete tax rule with id: {}", id);

        log.trace("Checking if tax rule with id {} exists", id);
        TaxRule taxRule = findById(id);
        log.trace("Tax rule id {} found. {}", id, taxRule);

        log.trace("Deleting tax rule with id {}", id);
        taxRuleRepository.delete(taxRule);
        log.trace("Tax rule with id {} deleted successfully", id);

        log.trace("Generating delete auditLog");
        auditLogService.deleteAuditLog(taxRule);
        log.trace("AuditLog generated successfully");

    }

    private TaxRule findById(@NotNull Long id) {
        return taxRuleRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException(
                String.format("Tax rule with id: %1$s not found", id)));
    }

    private TaxRule save(TaxRule taxRule) {
        log.trace("Saving tax rule: {}.", taxRule);
        TaxRule taxRuleCreated = taxRuleRepository.save(taxRule);
        log.trace("Tax rule saved successfully: {}.", taxRuleCreated);
        return taxRuleCreated;
    }

    private void performValidations(TaxRule taxRule) throws BusinessRuleException {
        log.trace("Checking if tax with id {} exist.", taxRule.getTax().getId());
        Tax tax = findTaxById(taxRule.getTax().getId());
        log.trace("Tax with id {} found: {}.", taxRule.getTax().getId(), tax);

        log.trace("Checking if rule type with id {} exist.", taxRule.getRuleType().getId());
        RuleType ruleType = findRuleTypeById(taxRule.getRuleType().getId());
        log.trace("Rule type with id {} found: {}.", taxRule.getRuleType().getId(), ruleType);

        log.trace("Checking if left and right variables are the same.");

        if (taxRule.getLeftVariable().getId().equals(taxRule.getRightVariable().getId())) {
            throw new BusinessRuleException("Left and right variables must not be the same.");
        }

        log.trace("Left and right variables are not the same.");

        log.trace("Checking if rule variable -left side- with id {} exist.",
            taxRule.getLeftVariable().getId());
        RuleVariable leftVariable = findRuleVariableById(taxRule.getLeftVariable().getId());
        log.trace("Rule variable -left side- with id {} found: {}.",
            taxRule.getLeftVariable().getId(), leftVariable);

        log.trace("Checking if rule variable -right side- with id {} exist.",
            taxRule.getRightVariable().getId());
        RuleVariable rightVariable = findRuleVariableById(taxRule.getRightVariable().getId());
        log.trace("Rule variable -right side- with id {} found: {}.",
            taxRule.getRightVariable().getId(), rightVariable);

        log.trace(
            "Checking if already exist a tax rule with the same tax, rule type, left, and right.");
        boolean existByTaxAndRuleTyeAndLeftVariableAndRightVariable = taxRuleRepository
            .existsByTaxIdAndRuleTypeIdAndLeftVariableIdAndRightVariableIdAndDeletedAtIsNull(
                tax.getId(), ruleType.getId(), leftVariable.getId(), rightVariable.getId());

        if (existByTaxAndRuleTyeAndLeftVariableAndRightVariable) {
            throw new BusinessRuleException(
                "There already exist a Tax Rule with the same tax, rule type, "
                    + "left variable, and right variable.");
        }

        log.trace("There is not a tax rule with the tax, rule type, left, and right.");
    }

    private TaxRule findTaxRuleById(Long id) {
        return taxRuleRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format("Tax rule with ID %1$s not found.", id)));
    }

    private RuleVariable findRuleVariableById(Long id) {
        return ruleVariableRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format("Rule Variable with ID %1$s not found.", id)));
    }

    private Tax findTaxById(Long id) {
        return taxRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format("Tax with ID %1$s not found.", id)));
    }

    private RuleType findRuleTypeById(Long id) {
        return ruleTypeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format("Rule Type with ID %1$s not found.", id)));
    }

    private void updateTaxRuleFields(TaxRule taxRule, TaxRule taxRuleChanges) {

        if (taxRuleChanges.getStatus() != null) {
            log.trace("Setting status: {}", taxRuleChanges.getStatus());
            taxRule.setStatus(taxRuleChanges.getStatus());
        }

        if(taxRuleChanges.getLeftVariable() != null) {
            log.trace("Setting left variable: {}", taxRuleChanges.getLeftVariable());
            taxRule.setLeftVariable(taxRuleChanges.getLeftVariable());
        }

        if(taxRuleChanges.getRightVariable() != null) {
            log.trace("Setting right variable: {}", taxRuleChanges.getRightVariable());
            taxRule.setRightVariable(taxRuleChanges.getRightVariable());
        }

        if(taxRuleChanges.getRuleType() != null) {
            log.trace("Setting rule type: {}", taxRuleChanges.getRuleType());
            taxRule.setRuleType(taxRuleChanges.getRuleType());
        }

        if(taxRuleChanges.getTax() != null) {
            log.trace("Setting tax: {}", taxRuleChanges.getTax());
            taxRule.setTax(taxRuleChanges.getTax());
        }

    }
}
