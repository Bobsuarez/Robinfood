package com.robinfood.configurations.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.models.CompanyBrand;
import com.robinfood.configurations.repositories.jpa.CompanyBrandRepository;
import com.robinfood.configurations.services.CompanyBrandService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CompanyBrandServiceImpl implements CompanyBrandService {

    private final CompanyBrandRepository companyBrandRepository;

    public CompanyBrandServiceImpl(
        CompanyBrandRepository companyBrandRepository) {
        this.companyBrandRepository = companyBrandRepository;
    }


    @Override
    @BasicLog
    public CompanyBrand findById(Long id) {
        return companyBrandRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
            String.format("Company brand with id '%1$d' not found.", id)));
    }

    @Override
    @BasicLog
    @Transactional(readOnly = true)
    public List<CompanyBrand> findAll() {
        return companyBrandRepository.findAll();
    }

    @Override
    @BasicLog
    @Transactional(readOnly = true)
    public Page<CompanyBrand> list(Long companyId, String name, Long storeId, Pageable pageable)
        throws JsonProcessingException {
        return companyBrandRepository
            .findByCompanyIdAndNameAndDeletedAtIsNull(companyId, name, storeId, pageable);
    }

    @Override
    public CompanyBrand getByCompanyIdAndMenuBrandId(Long menuBrandId) throws JsonProcessingException {
        return companyBrandRepository.findByCompanyIdAndMenuBrandId(menuBrandId);
    }
}
