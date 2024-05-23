package com.robinfood.configurations.repositories.jpa;

import com.robinfood.configurations.models.Country;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends SoftDeleteRepository<Country, Long> {
}
