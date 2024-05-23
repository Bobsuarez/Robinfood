package com.robinfood.configurations.repositories.jpa;

import com.robinfood.configurations.models.Headquarter;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeadquartersRepository extends SoftDeleteRepository<Headquarter, Long> {

    Optional<Headquarter> findByCompanyId(Long id);
}
