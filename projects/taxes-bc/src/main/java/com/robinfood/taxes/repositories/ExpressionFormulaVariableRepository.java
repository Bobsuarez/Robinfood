package com.robinfood.taxes.repositories;

import com.robinfood.taxes.models.ExpressionFormulaVariable;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpressionFormulaVariableRepository extends
    SoftDeleteRepository<ExpressionFormulaVariable, Long> {

    boolean existsByFormulaVariableIdAndDeletedAtIsNull(Long formulaVariableId);

    boolean existsByExpressionIdAndFormulaVariableIdAndDeletedAtIsNull(Long expressionId,
        Long formulaVariableId);

    boolean existsByExpressionIdAndDeletedAtIsNull(Long expressionId);

}
