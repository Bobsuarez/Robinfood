package com.robinfood.configurations.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.exceptions.BusinessRuleException;
import com.robinfood.configurations.models.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoreService {

    Store findById(Long storeId);

    Store create(Store receivedStore) throws JsonProcessingException, BusinessRuleException;

    Store update(Long id, Store receivedStore) throws JsonProcessingException, BusinessRuleException;

    int countByFilter(String name, Long companyCountryId);

    Page<Store> list(String name, Long companyCountryId,Pageable pageable ) throws JsonProcessingException;

    void delete(Long id) throws JsonProcessingException;

    int getCompanyId(Long id) throws JsonProcessingException;

    List<Store> findStores(String name);

}
