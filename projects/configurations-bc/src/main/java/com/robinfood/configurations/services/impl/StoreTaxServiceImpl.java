package com.robinfood.configurations.services.impl;

import com.robinfood.configurations.models.StoreTax;
import com.robinfood.configurations.repositories.jpa.StoreTaxRepository;
import com.robinfood.configurations.services.StoreTaxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class StoreTaxServiceImpl implements StoreTaxService {

    private final StoreTaxRepository storeTaxRepository;

    public StoreTaxServiceImpl(StoreTaxRepository storeTaxRepository) {
        this.storeTaxRepository = storeTaxRepository;
    }

    @Override
    public List<StoreTax> findByIdStore(Long storeId) {

        return storeTaxRepository.findByStoresIdIs(storeId);
    }
}
