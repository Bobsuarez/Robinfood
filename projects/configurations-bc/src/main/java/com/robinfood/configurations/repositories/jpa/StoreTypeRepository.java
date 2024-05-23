package com.robinfood.configurations.repositories.jpa;

import com.robinfood.configurations.models.StoreType;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreTypeRepository extends SoftDeleteRepository<StoreType, Long> {

    boolean existsByIdAndDeletedAtIsNull(Long id);

}
