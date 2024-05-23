package com.robinfood.taxes.repositories;

import com.robinfood.taxes.models.FamilyType;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyTypeRepository extends SoftDeleteRepository<FamilyType, Long> {

}
