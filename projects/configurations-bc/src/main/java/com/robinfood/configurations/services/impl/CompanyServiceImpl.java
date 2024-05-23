package com.robinfood.configurations.services.impl;

import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.models.Company;
import com.robinfood.configurations.repositories.jpa.CompanyRepository;
import com.robinfood.configurations.services.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    @BasicLog
    @Transactional(readOnly = true)
    public Company findById(Long id) {
        Optional<Company> company = Optional.ofNullable(companyRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Company id not found")));
        return company.get();
    }

    @Override
    @BasicLog
    @Transactional(readOnly = true)
    public List<Company> findByAll(Long status) {
        return companyRepository.findAllByStatus(status);
    }

}
