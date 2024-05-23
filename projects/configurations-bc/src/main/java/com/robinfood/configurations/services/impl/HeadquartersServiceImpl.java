package com.robinfood.configurations.services.impl;

import com.robinfood.configurations.models.Headquarter;
import com.robinfood.configurations.repositories.jpa.HeadquartersRepository;
import com.robinfood.configurations.services.HeadquartersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class HeadquartersServiceImpl implements HeadquartersService {

    private final HeadquartersRepository repository;

    public HeadquartersServiceImpl(HeadquartersRepository repository) {
        this.repository = repository;
    }

    /**
     * Método que consulta la informacion de la compañia por pais
     *
     * @return objeto de headquarters por compañia pais
     */
    @Override
    public Headquarter getByCompanyCountryId(Long companyCountryId) {
        return repository.findByCompanyId(companyCountryId).orElseThrow(() -> new EntityNotFoundException(
            String.format("Brand with companyCountryId %d does not exists.", companyCountryId)));
    }
}
