package com.robinfood.taxes.services.impl;

import static com.robinfood.taxes.constants.GeneralConstants.ACTIVE_STATUS;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.models.Family;
import com.robinfood.taxes.models.FamilyType;
import com.robinfood.taxes.repositories.FamilyRepository;
import com.robinfood.taxes.repositories.FamilyTypeRepository;
import com.robinfood.taxes.repositories.TaxRepository;
import com.robinfood.taxes.services.AuditLogService;
import com.robinfood.taxes.services.FamilyService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class FamilyServiceImpl implements FamilyService {

    private final FamilyRepository familyRepository;

    private final FamilyTypeRepository familyTypeRepository;

    private final TaxRepository taxRepository;

    private final AuditLogService auditLogService;

    public FamilyServiceImpl(FamilyRepository familyRepository,
        FamilyTypeRepository familyTypeRepository,
        TaxRepository taxRepository,
        AuditLogService auditLogService) {
        this.familyRepository = familyRepository;
        this.familyTypeRepository = familyTypeRepository;
        this.taxRepository = taxRepository;
        this.auditLogService = auditLogService;
    }

    @BasicLog
    @Override
    public List<Family> list(Long countryId, Long familyTypeId, Integer status) {
        return familyRepository
            .findByCountryIdAndFamilyTypeIdAndStatus(countryId, familyTypeId, status);
    }

    public Family create(@NotNull final Family family)
        throws JsonProcessingException, BusinessRuleException {
        log.trace("Starting process to create family on service with object {}.", family);

        log.trace("Checking if the family type with id {} exists.", family.getFamilyType().getId());
        FamilyType familyType = findFamilyTypeById(family.getFamilyType().getId());
        log.trace("Family type with id {} found: {}.", familyType.getId(), familyType);

        log.trace("Validating if family with CountryId {} and Name {} exist.",
            family.getCountryId(), family.getName());
        validateFamilyOnCreate(family.getCountryId(), family.getName());
        log.trace("Family with CountryId {} and Name {} does not exist.",
            family.getCountryId(), family.getName());

        log.trace("Saving family: {}.", family);
        Family familyCreated = familyRepository.save(family);
        log.trace("Family saved successfully: {}.", familyCreated);

        auditLogService.createAuditLog(familyCreated);

        log.trace("Process to create family on service ended successfully.");

        return familyCreated;
    }

    @BasicLog
    @Override
    public Family update(Long id, @NotNull Family newFamily)
        throws JsonProcessingException, BusinessRuleException {
        log.trace("Starting process on update family service with object {}", newFamily);

        log.trace("Checking if family with id {} exists", id);
        Family family = findById(id);
        log.trace("Family with id {} found. {}", id, family);

        Family beforeUpdate = family.toBuilder().build();

        updateFamilyFields(family, newFamily);

        log.trace("Saving family changes. {}", family);
        Family updatedFamily = familyRepository.save(family);
        log.trace("Family updated successfully. {}", updatedFamily);

        auditLogService.updateAuditLog(beforeUpdate, updatedFamily);

        return updatedFamily;
    }

    @BasicLog
    @Override
    public void delete(Long id) throws JsonProcessingException, BusinessRuleException {
        log.trace("Starting process on service delete family with id: {}", id);

        log.trace("Checking if family with id {} exists", id);
        Family family = findById(id);
        log.trace("Family id {} found. {}", id, family);

        log.trace("Checking if family with id {} is active on taxes", id);
        if(taxRepository.existsByFamilyIdAndStatusAndDeletedAtIsNull(id, ACTIVE_STATUS)) {
            BusinessRuleException ex = new BusinessRuleException(
                String.format("Family with id %s is active for one or more taxes", id));
            log.error(ex.getMessage(), ex);
            throw ex;
        }
        log.trace("Family with id {} is not active for any taxes", id);

        log.trace("Deleting family with id {}", family);
        familyRepository.delete(family);
        log.trace("Family with id {} deleted successfully", id);

        log.trace("Generating delete auditLog");
        auditLogService.deleteAuditLog(family);
        log.trace("AuditLog generated successfully");

    }

    private Family findById(@NotNull Long id) {
        return familyRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException(
                String.format("Family with id: %1$s not found", id)));
    }

    private FamilyType findFamilyTypeById(Long id) {
        return familyTypeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format("Family type with id %1$s not found.", id)));
    }

    private void validateNameUniqueByCountry(@NotNull String name, @NotNull Long country,
        Long family)
        throws BusinessRuleException {
        boolean nameAndCountry =
            familyRepository
                .existsByCountryIdAndNameAndDeletedAtIsNullAndIdNot(country, name, family);
        if (nameAndCountry) {
            throw new BusinessRuleException(
                String.format("Family is not unique for country %1$s with name %2$s",
                    country, name));
        }
    }

    private void updateFamilyFields(@NotNull Family oldFamily, @NotNull Family newFamily)
        throws BusinessRuleException {
        long country = oldFamily.getCountryId();

        if (newFamily.getName() != null) {
            log.trace("Validate unique name {} ", newFamily.getName());
            validateNameUniqueByCountry(newFamily.getName(), country,
                newFamily.getFamilyType().getId());
            log.trace("Name is unique");
            oldFamily.setName(newFamily.getName());
        }

        if (newFamily.getFamilyType() != null) {
            FamilyType familyType = findFamilyTypeById(
                newFamily.getFamilyType().getId());
            oldFamily.setFamilyType(familyType);
        }

        if (newFamily.getStatus() != null
            && !newFamily.getStatus().equals(oldFamily.getStatus())) {
            oldFamily.setStatus(newFamily.getStatus());
        }
    }

    private void validateFamilyOnCreate(@NotNull final Long countryId, @NotNull final String name)
        throws BusinessRuleException {
        boolean exists =
            familyRepository.existsByCountryIdAndNameAndDeletedAtIsNull(countryId, name);

        if (exists) {
            throw new BusinessRuleException(
                String.format("Family with CountryID %1$d and Name %2$s already exist.",
                    countryId, name));
        }
    }

}
