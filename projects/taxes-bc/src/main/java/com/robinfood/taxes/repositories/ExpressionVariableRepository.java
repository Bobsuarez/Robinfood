package com.robinfood.taxes.repositories;

import com.robinfood.taxes.models.ExpressionFormulaVariable;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpressionVariableRepository extends SoftDeleteRepository<ExpressionFormulaVariable, Long> {

}
