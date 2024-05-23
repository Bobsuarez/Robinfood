package com.robinfood.configurations.repositories.jpa;

import com.robinfood.configurations.models.Zone;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends SoftDeleteRepository<Zone, Long> {

    boolean existsByIdAndDeletedAtIsNull(Long id);

}
