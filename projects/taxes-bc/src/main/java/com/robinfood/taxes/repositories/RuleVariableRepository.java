package com.robinfood.taxes.repositories;

import com.robinfood.taxes.models.RuleVariable;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleVariableRepository extends SoftDeleteRepository<RuleVariable, Long> {

    RuleVariable findByIdAndDeletedAtIsNull(Long variableId);

    Boolean existsByPathAndNameAndDeletedAtIsNull(String path, String name);

    List<RuleVariable> deletedAtIsNull();

}
