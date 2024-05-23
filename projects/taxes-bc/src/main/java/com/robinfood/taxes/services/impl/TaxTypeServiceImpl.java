package com.robinfood.taxes.services.impl;

import static com.robinfood.taxes.constants.PermissionsConstants.ACTIVE;
import static com.robinfood.taxes.constants.PermissionsConstants.INACTIVE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.TaxType;
import com.robinfood.taxes.repositories.TaxRepository;
import com.robinfood.taxes.repositories.TaxTypeRepository;
import com.robinfood.taxes.services.AuditLogService;
import com.robinfood.taxes.services.TaxTypeService;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class TaxTypeServiceImpl implements TaxTypeService {

    private final TaxRepository taxRepository;
    private final TaxTypeRepository taxTypeRepository;

    private final AuditLogService auditLogService;

    private static final List<Integer> VALID_STATUS = Arrays.asList(ACTIVE, INACTIVE);

    public TaxTypeServiceImpl(TaxRepository taxRepository,
        TaxTypeRepository taxTypeRepository,
        AuditLogService auditLogService) {
        this.taxRepository = taxRepository;
        this.taxTypeRepository = taxTypeRepository;
        this.auditLogService = auditLogService;
    }


    @BasicLog
    @Override
    public List<TaxType> list(Long countryId, Integer status) {
        log.trace("Starting process on list tax type service with country id {} and status {}",
            countryId, status);
        return taxTypeRepository.findByCountryId(countryId, status);
    }

    @BasicLog
    @Override
    public TaxType create(TaxType taxType) throws JsonProcessingException, BusinessRuleException {
        log.trace("Starting process to create taxType type with object: {}", taxType);

        log.trace("Checking if name '{}' exist for countryId {}", taxType.getName(),
            taxType.getCountryId());
        boolean exists = taxTypeRepository
            .existsByNameAndCountryIdAndDeletedAtIsNull(taxType.getName(), taxType.getCountryId());

        if (exists) {
            BusinessRuleException ex = new BusinessRuleException(
                String.format("Tax type with name '%1$s' already exists for country_id %2$s",
                    taxType.getName(), taxType.getCountryId()));
            log.error(ex.getMessage(), ex);
            throw ex;
        }
        log.trace("Name '{}' is valid", taxType.getName());

        log.trace("Setting default status {}", ACTIVE);
        taxType.setStatus(ACTIVE);

        log.trace("Saving taxType type on DB. {}", taxType);
        TaxType createdTax = taxTypeRepository.save(taxType);
        log.trace("Tax type created successfully on DB. {}", createdTax);

        log.trace("Saving audit log for taxType type creation");
        auditLogService.createAuditLog(createdTax);
        log.trace("Audit log for taxType type creation saved successfully");

        return createdTax;

    }

    @BasicLog
    @Override
    public TaxType update(Long id, TaxType taxTypeToSave)
        throws JsonProcessingException, BusinessRuleException {

        TaxType oldTaxType = taxTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
            String.format("Tax type with id: '%1$s' not found", id)));

        TaxType beforeUpdate = oldTaxType.toBuilder().build();

        updateTaxTypeFields(oldTaxType, taxTypeToSave);

        log.trace("Saving Tax Type changes. {}", oldTaxType);
        TaxType updatedTaxType = taxTypeRepository.save(oldTaxType);
        log.trace("Tax type updated successfully. {}", updatedTaxType);

        auditLogService.updateAuditLog(beforeUpdate, updatedTaxType);

        return updatedTaxType;
    }

    private void updateTaxTypeFields(TaxType oldTaxType, TaxType newTaxType) throws BusinessRuleException {

        if (newTaxType.getName() != null && !newTaxType.getName().equals(oldTaxType.getName())) {

            log.trace("Checking if Tax Type with name {} and country id {} already exist",
                oldTaxType.getName(), oldTaxType.getCountryId());

            if (isUniqueNameAndCountry(newTaxType.getName(), oldTaxType.getCountryId())) {
                throw new BusinessRuleException(String
                    .format("Tax type with name: %1$s and country id: %2$s already exists",
                        newTaxType.getName(), oldTaxType.getCountryId()));
            }

            log.trace("Tax type with name {} and country id {} not exist",
                oldTaxType.getName(), oldTaxType.getCountryId());

            oldTaxType.setName(newTaxType.getName());
        }

        if (newTaxType.getStatus() != null && !newTaxType.getStatus().equals(oldTaxType.getStatus())) {

            log.trace("Checking if Tax type status {} is valid", newTaxType.getStatus());
            if (!VALID_STATUS.contains(newTaxType.getStatus())) {
                throw new BusinessRuleException(
                    String.format("Status %1$s not valid", newTaxType.getStatus()));
            }
            log.trace("Tax type status {} is valid", newTaxType.getStatus());

            oldTaxType.setStatus(newTaxType.getStatus());
        }
    }

    @Override
    public void delete(Long id) throws JsonProcessingException, BusinessRuleException {
        log.trace("Starting process on service delete tax type with id: {}", id);

        log.trace("Checking if tax type with id {} exists", id);
        TaxType taxType = findById(id);
        log.trace("Tax type id {} found. {}", id, taxType);

        log.trace("Checking if tax type with id {} is active on taxes", id);
        if(taxRepository.existsByTaxTypeIdAndDeletedAtIsNull(id)) {
            throw new BusinessRuleException(
                    String.format("Tax type with id %s is active for one or more taxes", id));
        }
        log.trace("Tax type with id {} is not active for any taxes", id);

        log.trace("Deleting tax type with id {}", id);
        taxTypeRepository.delete(taxType);
        log.trace("Tax type with id {} deleted successfully", id);

        log.trace("Generating delete auditLog");
        auditLogService.deleteAuditLog(taxType);
        log.trace("AuditLog generated successfully");
    }

    private TaxType findById(@NotNull Long id) {
        return taxTypeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Tax type with id: %1$s not found", id)));
    }

    private boolean isUniqueNameAndCountry(String name, Long countryId) {
        return taxTypeRepository.existsByNameAndCountryIdAndDeletedAtIsNull(name, countryId);
    }
}
