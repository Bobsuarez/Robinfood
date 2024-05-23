package com.robinfood.taxes.services.impl;

import static com.robinfood.taxes.constants.PermissionsConstants.ACTIVE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.Expression;
import com.robinfood.taxes.models.Family;
import com.robinfood.taxes.models.Tax;
import com.robinfood.taxes.models.TaxType;
import com.robinfood.taxes.repositories.TaxRuleRepository;
import com.robinfood.taxes.repositories.TaxRepository;
import com.robinfood.taxes.repositories.FamilyRepository;
import com.robinfood.taxes.repositories.TaxTypeRepository;
import com.robinfood.taxes.repositories.ExpressionRepository;
import com.robinfood.taxes.services.AuditLogService;
import com.robinfood.taxes.services.TaxService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class TaxServiceImpl implements TaxService {

    private final TaxRepository taxRepository;
    private final TaxRuleRepository taxRuleRepository;
    private final FamilyRepository familyRepository;
    private final TaxTypeRepository taxTypeRepository;
    private final ExpressionRepository expressionRepository;

    private final AuditLogService auditLogService;

    public TaxServiceImpl(TaxRepository taxRepository,
        TaxRuleRepository taxRuleRepository,
        FamilyRepository familyRepository,
        TaxTypeRepository taxTypeRepository,
        ExpressionRepository expressionRepository,
        AuditLogService auditLogService) {
        this.taxRepository = taxRepository;
        this.taxRuleRepository = taxRuleRepository;
        this.familyRepository = familyRepository;
        this.taxTypeRepository = taxTypeRepository;
        this.expressionRepository = expressionRepository;
        this.auditLogService = auditLogService;
    }


    @Override
    @BasicLog
    public List<Tax> list(Long familyId) {
        log.trace("Starting process on listing Taxes with Family id {}", familyId);
        return taxRepository.findByFamilyIdAndDeletedAtIsNull(familyId);
    }

    @Override
    @BasicLog
    @Transactional(rollbackOn = Exception.class)
    public Tax create(Tax tax) throws JsonProcessingException, BusinessRuleException {

        log.trace("Checking if family with id {} exists", tax.getFamily().getId());
        Family family = getFamily(tax.getFamily().getId());
        log.trace("Family with id {} found", family);

        log.trace("Checking if expression with id {} exists", tax.getExpression().getId());
        Expression expression = getExpression(tax.getExpression().getId());
        log.trace("Expression with id {} found", expression);

        log.trace("Checking if tax type with id {} exists", tax.getTaxType().getId());
        TaxType taxType = getTaxType(tax.getTaxType().getId());
        log.trace("Tax type with id {} found", taxType);

        tax.setStatus(ACTIVE);

        if (!family.getCountryId().equals(taxType.getCountryId())) {
            throw new BusinessRuleException("Family and tax type are from different countries");
        }

        log.trace("Saving tax on DB. {}", tax);
        Tax createdTax = taxRepository.save(tax);
        log.trace("Tax created successfully on DB. {}", createdTax);

        log.trace("Saving audit log for tax creation");
        auditLogService.createAuditLog(createdTax);
        log.trace("Audit log for tax creation saved successfully");

        return createdTax;
    }


    @Override
    public Tax update(Long id, Tax taxChanges)
        throws JsonProcessingException, BusinessRuleException {

        Tax taxToSave = taxRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
            String.format("Tax with id: '%1$s' not found", id)));

        updateTaxFields(taxToSave, taxChanges);
        updateTaxRelationsFields(taxToSave, taxChanges);

        if (!taxToSave.getFamily().getCountryId().equals(taxToSave.getTaxType().getCountryId())) {
            throw new BusinessRuleException("Family and tax type are from different countries");
        }

        log.trace("Saving Tax changes. {}", taxToSave);
        Tax updatedTax = taxRepository.save(taxToSave);
        log.trace("Tax updated successfully. {}", updatedTax);

        Tax beforeUpdate = taxToSave.toBuilder().build();

        auditLogService.updateAuditLog(beforeUpdate, updatedTax);

        return updatedTax;
    }

    @BasicLog
    @Override
    public void delete(Long id) throws JsonProcessingException, BusinessRuleException {
        log.trace("Starting process on service delete tax with ID: {}", id);

        log.trace("Checking if tax with ID {} exists", id);
        Tax tax = findById(id);
        log.trace("Tax ID {} found. {}", id, tax);

        log.trace("Checking if tax with ID {} is active on tax_rules", id);
        if (taxRuleRepository.existsByTaxIdAndStatusAndDeletedAtIsNull(id, ACTIVE)) {
            throw new BusinessRuleException(
                String.format("Tax with ID %s is active for one or more tax_rules", id));
        }
        log.trace("Tax with ID {} is not active for any tax_rules", id);

        log.trace("Deleting tax with ID {}", tax);
        taxRepository.delete(tax);
        log.trace("Tax with ID {} deleted successfully", id);

        log.trace("Generating delete auditLog");
        auditLogService.deleteAuditLog(tax);
        log.trace("AuditLog generated successfully");
    }

    private Tax findById(@NotNull Long id) {
        return taxRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException(
                String.format("Tax with id: %1$s not found", id)));
    }

    private static void updateTaxFields(Tax taxToSave, Tax taxChanges) {

        if (taxChanges.getName() != null) {
            taxToSave.setName(taxChanges.getName());
        }

        if (taxChanges.getDescription() != null) {
            taxToSave.setDescription(taxChanges.getDescription());
        }

        if (taxChanges.getValue() != null) {
            taxToSave.setValue(taxChanges.getValue());
        }

        if (taxChanges.getStatus() != null) {
            taxToSave.setStatus(taxChanges.getStatus());
        }

        if (taxChanges.getApplyRules() != null) {
            taxToSave.setApplyRules(taxChanges.getApplyRules());
        }

        if (taxChanges.getSapId() != null) {
            taxToSave.setSapId(taxChanges.getSapId());
        }

    }

    private void updateTaxRelationsFields(Tax taxToSave, Tax taxChanges) {

        Family newFamily = taxChanges.getFamily();
        if (newFamily != null && !newFamily.getId().equals(taxToSave.getFamily().getId())) {
            log.trace("Checking if family with id {} exists", newFamily.getId());
            Family family = getFamily(newFamily.getId());
            log.trace("Family with id {} found. {}", family.getId(), family);

            taxToSave.setFamily(family);
        }

        TaxType newTaxType = taxChanges.getTaxType();
        if (newTaxType != null && !newTaxType.getId().equals(taxToSave.getTaxType().getId())) {
            log.trace("Checking if tax type with id {} exists", newTaxType.getId());
            TaxType taxType = getTaxType(newTaxType.getId());
            log.trace("Tax type with id {} found. {}", taxType.getId(), taxType);

            taxToSave.setTaxType(taxType);
        }

        Expression newExpression = taxChanges.getExpression();
        if (newExpression != null && !newExpression.getId()
            .equals(taxToSave.getExpression().getId())) {
            log.trace("Checking if Expression with id {} exists", newExpression.getId());
            Expression expression = getExpression(newExpression.getId());
            log.trace("Expression type with id {} found. {}", expression.getId(), expression);

            taxToSave.setExpression(expression);
        }
    }

    private Family getFamily(Long id) {
        return familyRepository.findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException(String.format("Family with id %s not found.", id))
            );
    }

    private TaxType getTaxType(Long id) {
        return taxTypeRepository.findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException(String.format("Tax type with id %s not found.", id))
            );
    }

    private Expression getExpression(Long id) {
        return expressionRepository.findById(id)
            .orElseThrow(() ->
                new EntityNotFoundException(String.format("Expression with id %s not found.", id))
            );
    }
}
