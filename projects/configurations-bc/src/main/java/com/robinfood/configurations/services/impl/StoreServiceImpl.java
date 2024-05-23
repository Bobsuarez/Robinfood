package com.robinfood.configurations.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.exceptions.BusinessRuleException;
import com.robinfood.configurations.models.City;
import com.robinfood.configurations.models.Company;
import com.robinfood.configurations.models.Store;
import com.robinfood.configurations.models.StoreIdentifierType;
import com.robinfood.configurations.models.StoreType;
import com.robinfood.configurations.models.Zone;
import com.robinfood.configurations.repositories.jpa.CityRepository;
import com.robinfood.configurations.repositories.jpa.CompanyRepository;
import com.robinfood.configurations.repositories.jpa.StoreIdentifierTypeRepository;
import com.robinfood.configurations.repositories.jpa.StoreRepository;
import com.robinfood.configurations.repositories.jpa.StoreTypeRepository;
import com.robinfood.configurations.repositories.jpa.ZoneRepository;
import com.robinfood.configurations.services.AuditLogService;
import com.robinfood.configurations.services.StoreService;
import com.robinfood.configurations.utils.JsonUtils;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final CompanyRepository companyRepository;
    private final CityRepository cityRepository;
    private final ZoneRepository zoneRepository;
    private final StoreTypeRepository storeTypeRepository;
    private final AuditLogService auditLogService;
    private final StoreIdentifierTypeRepository storeIdentifierTypeRepository;

    public StoreServiceImpl(StoreRepository storeRepository,
                            CompanyRepository companyRepository, CityRepository cityRepository,
                            ZoneRepository zoneRepository,
                            StoreTypeRepository storeTypeRepository,
                            AuditLogService auditLogService,
                            StoreIdentifierTypeRepository storeIdentifierTypeRepository) {
        this.storeRepository = storeRepository;
        this.companyRepository = companyRepository;
        this.cityRepository = cityRepository;
        this.zoneRepository = zoneRepository;
        this.storeTypeRepository = storeTypeRepository;
        this.auditLogService = auditLogService;
        this.storeIdentifierTypeRepository = storeIdentifierTypeRepository;
    }

    @Override
    @BasicLog
    @Transactional(readOnly = true)
    public Store findById(Long storeId) {
        return storeRepository.findById(storeId).orElseThrow(() ->
            new EntityNotFoundException(String.format("Store with id %d not found.", storeId)));
    }

    @Override
    public Store create(Store receivedStore) throws BusinessRuleException, JsonProcessingException {
        log.info("Starting process to CREATE STORE.");

        validateDuplicatedName(receivedStore.getName());
        log.info("Name is valid.");

        validateDuplicatedInternalName(receivedStore.getInternalName());
        log.info("Internal name is valid.");

        validateDuplicatedUuid(Objects.requireNonNullElse(receivedStore.getUuid(), Strings.EMPTY));
        log.info("UUID is valid.");

        Company company = validatedCompany(receivedStore.getCompany().getId());
        log.info("Company country id is valid.");

        City city = validatedCountry(receivedStore.getCity().getId());
        log.info("City id is valid.");

        Zone zone = validatedZone(receivedStore.getZones().getId());
        log.info("Zone id is valid.");

        StoreType storeType = validatedStoreType(receivedStore.getStoreType().getId());
        log.info("Store type id is valid.");

        StoreIdentifierType storeIdentifierType = validatedStoreIdentifierType(
            receivedStore.getStoreIdentifierType().getId());
        log.info("Store identifier type id is valid.");

        receivedStore.setStoreType(storeType);
        receivedStore.setZones(zone);
        receivedStore.setCity(city);
        receivedStore.setCompany(company);
        receivedStore.setStoreIdentifierType(storeIdentifierType);
        receivedStore.setUuid(
            Objects.requireNonNullElse(receivedStore.getUuid(), UUID.randomUUID().toString()));

        Store store = storeRepository.save(receivedStore);
        log.info("Store saved successfully: {}", store);

        auditLogService.createAuditLog(store);

        return store;
    }

    @BasicLog
    @Override
    @Transactional
    public Store update(Long id, Store receivedStore)
        throws JsonProcessingException, BusinessRuleException {
        log.info("Starting update process on service.");

        Store storeToSaveDB = findById(id);
        log.info("Store with id {} found.", id);

        Store beforeUpdate = storeToSaveDB.toBuilder().build();

        if (!storeToSaveDB.getName()
            .equalsIgnoreCase(Objects.requireNonNullElse(receivedStore.getName(), Strings.EMPTY))) {
            validateDuplicatedName(receivedStore.getName());
            log.info("Name is valid.");
        }

        if (!storeToSaveDB.getInternalName()
            .equalsIgnoreCase(Objects.requireNonNullElse(receivedStore.getInternalName(), Strings.EMPTY))) {
            validateDuplicatedInternalName(receivedStore.getInternalName());
            log.info("Internal name is valid.");
        }

        if (!storeToSaveDB.getUuid()
            .equalsIgnoreCase(Objects.requireNonNullElse(receivedStore.getUuid(), Strings.EMPTY))) {
            validateDuplicatedUuid(Objects.requireNonNullElse(receivedStore.getUuid(), Strings.EMPTY));
            log.info("UUID is valid.");
        }

        Company company = validatedCompany(
            Objects.requireNonNullElse(receivedStore.getCompany().getId(),
                beforeUpdate.getCompany().getId()));
        log.info("Company country id is valid.");

        City city = validatedCountry(
            Objects.requireNonNullElse(receivedStore.getCity().getId(),
                beforeUpdate.getCity().getId()));
        log.info("City id is valid.");

        Zone zone = validatedZone(
            Objects.requireNonNullElse(receivedStore.getZones().getId(),
                beforeUpdate.getZones().getId()));
        log.info("Zone id is valid.");

        StoreType storeType = validatedStoreType(
            Objects.requireNonNullElse(receivedStore.getStoreType().getId(),
                beforeUpdate.getStoreType().getId()));
        log.info("Store type id is valid.");

        StoreIdentifierType storeIdentifierType = validatedStoreIdentifierType(
            Objects.requireNonNullElse(receivedStore.getStoreIdentifierType().getId(),
                beforeUpdate.getStoreIdentifierType().getId()));
        log.info("Store identifier type id is valid.");

        storeToSaveDB.setStoreType(storeType);
        storeToSaveDB.setZones(zone);
        storeToSaveDB.setCity(city);
        storeToSaveDB.setCompany(company);
        storeToSaveDB.setStoreIdentifierType(storeIdentifierType);
        storeToSaveDB.setUuid(Objects.requireNonNullElse(receivedStore.getUuid(),
            Objects.requireNonNullElse(beforeUpdate.getUuid(), UUID.randomUUID().toString())));

        updateStoreFields(storeToSaveDB, receivedStore);

        Store updatedDiscount = storeRepository.save(storeToSaveDB);
        log.info("Discount updated successfully.");

        auditLogService.updateAuditLog(beforeUpdate, updatedDiscount);

        return updatedDiscount;
    }

    @Override
    @BasicLog
    @Transactional(readOnly = true)
    public int countByFilter(String name, Long companyCountryId) {
        return storeRepository.countByNameAndCompanyCountryIdDeletedAtIsNull(name, companyCountryId);
    }

    @Override
    @BasicLog
    @Transactional(readOnly = true)
    public Page<Store> list(String name, Long companyCountryId, Pageable pageable) throws JsonProcessingException {
        log.info("Listing store");
        Page<Store> storeList = storeRepository
            .findByNameAndCompanyCountryIdDeletedAtIsNull(name, companyCountryId, pageable);
        log.info("List of store obtained successfully {}", JsonUtils.convertToJson(storeList));
        return storeList;
    }

    @Override
    @BasicLog
    public void delete(Long id) throws JsonProcessingException {
        Store store = storeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format("Store with ID %s not found", id)));
        log.info("Starting process to delete Store service.");

        auditLogService.deleteAuditLog(store);

        log.info("Deleting Store.");
        storeRepository.delete(store);
        log.info("Store deleted successfully with ID {}", id);
    }

    @Override
    public int getCompanyId(Long id) {
        log.info("Geting company by storeId");
        return storeRepository.getCompanyIdByStoreId(id);
    }

    @Override
    @BasicLog
    @Transactional(readOnly = true)
    public List<Store> findStores(String name) {
        if(name != null && !name.isBlank()) {
            return storeRepository.findByNameContains(name);
        } else {
            return storeRepository.findAll();
        }
    }

    private StoreType validatedStoreType(Long id) throws BusinessRuleException {
        return storeTypeRepository.findById(id)
            .orElseThrow(() -> new BusinessRuleException(
                String.format("Store type with id '%1$s' does not exists", id)));

    }

    private Zone validatedZone(Long id) throws BusinessRuleException {
        return zoneRepository.findById(id)
            .orElseThrow(() -> new BusinessRuleException(
                String.format("Zone with id '%1$s' does not exists", id)));
    }

    private City validatedCountry(Long id) throws BusinessRuleException {
        return cityRepository.findById(id)
            .orElseThrow(() -> new BusinessRuleException(
                String.format("City with id '%1$s' does not exists", id)));
    }

    private Company validatedCompany(Long id) throws BusinessRuleException {
        return companyRepository.findById(id)
            .orElseThrow(() -> new BusinessRuleException(
                String.format("Company country with id '%1$s' does not exists", id)));

    }

    private void validateDuplicatedName(String storeName) throws BusinessRuleException {
        boolean isDuplicatedName = storeRepository.existsByNameAndDeletedAtIsNull(storeName);

        if (isDuplicatedName) {
            BusinessRuleException e =
                new BusinessRuleException(
                    String.format("Store with name '%1$s' already exists", storeName));
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    private void validateDuplicatedInternalName(String internalName) throws BusinessRuleException {
        boolean isDuplicatedInternalName = storeRepository.existsByInternalNameAndDeletedAtIsNull(
            internalName);

        if (isDuplicatedInternalName) {
            BusinessRuleException e =
                new BusinessRuleException(
                    String.format("Store with internal name '%1$s' already exists", internalName));
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    private StoreIdentifierType validatedStoreIdentifierType(Long id) throws BusinessRuleException {
        return storeIdentifierTypeRepository.findById(id)
            .orElseThrow(() -> new BusinessRuleException(
                String.format("Store identifier type with id '%1$s' does not exists", id)));

    }

    private void validateDuplicatedUuid(String uuid) throws BusinessRuleException {
        boolean isDuplicatedInternalName = storeRepository.existsByUuidAndDeletedAtIsNull(uuid);

        if (isDuplicatedInternalName) {
            BusinessRuleException e =
                new BusinessRuleException(
                    String.format("Store with UUID '%1$s' already exists", uuid));
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    private void updateStoreFields(Store storeToSaveDB, Store receivedStore) {
        storeToSaveDB.setName(
            Objects.requireNonNullElse(receivedStore.getName(), storeToSaveDB.getName()));
        storeToSaveDB.setInternalName(Objects.requireNonNullElse(receivedStore.getInternalName(),
            storeToSaveDB.getInternalName()));
        storeToSaveDB.setAddress(
            Objects.requireNonNullElse(receivedStore.getAddress(), storeToSaveDB.getAddress()));
        storeToSaveDB.setLocation(Objects.requireNonNullElse(receivedStore.getLocation(),
            String.valueOf(storeToSaveDB.getLocation())));
        storeToSaveDB.setPhone(Objects.requireNonNullElse(receivedStore.getPhone(),
            String.valueOf(storeToSaveDB.getPhone())));
        storeToSaveDB.setEmail(Objects.requireNonNullElse(receivedStore.getEmail(),
            String.valueOf(storeToSaveDB.getEmail())));
        storeToSaveDB.setIdentifier(Objects.requireNonNullElse(receivedStore.getIdentifier(),
            String.valueOf(storeToSaveDB.getIdentifier())));
        storeToSaveDB.setCurrencyType(Objects.requireNonNullElse(receivedStore.getCurrencyType(),
            String.valueOf(storeToSaveDB.getCurrencyType())));
        storeToSaveDB.setCurrencySymbol(
            Objects.requireNonNullElse(receivedStore.getCurrencySymbol(),
                String.valueOf(storeToSaveDB.getCurrencySymbol())));
        storeToSaveDB.setTaxRegime(Objects.requireNonNullElse(receivedStore.getTaxRegime(),
            String.valueOf(storeToSaveDB.getTaxRegime())));

    }

}
