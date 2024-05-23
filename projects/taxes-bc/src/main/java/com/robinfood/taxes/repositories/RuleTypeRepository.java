package com.robinfood.taxes.repositories;

import com.robinfood.taxes.models.RuleType;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleTypeRepository extends SoftDeleteRepository<RuleType, Long> {

}
