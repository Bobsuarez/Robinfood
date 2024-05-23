package com.robinfood.configurations.repositories.jpa;

import com.robinfood.configurations.models.StoreIdentifierType;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreIdentifierTypeRepository extends SoftDeleteRepository<StoreIdentifierType, Long> {

    boolean existsByIdAndDeletedAtIsNull(Long id);

}
