package com.robinfood.configurations.services.impl;

import com.robinfood.configurations.annotations.BasicLog;
import com.robinfood.configurations.models.Country;
import com.robinfood.configurations.repositories.jpa.CountryRepository;
import com.robinfood.configurations.services.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CountryServiceImpl implements CountryService {
    private final CountryRepository repository;

    public CountryServiceImpl(
        CountryRepository repository
    ) {
        this.repository = repository;
    }

    /**
     * Método que obtiene el listado de todos los países
     *
     * @return List<Country> Listado de países
     */
    @Override
    @BasicLog
    @Transactional(readOnly = true)
    public List<Country> findAllCountries() {
        return repository.findAll(
            Sort.by(Sort.Direction.ASC, "name"));
    }
}
