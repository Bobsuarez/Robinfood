package com.robinfood.taxes.repositories;

import com.robinfood.taxes.domain.MappedVariable;
import com.robinfood.taxes.models.FormulaVariable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FormulaVariableRepository extends SoftDeleteRepository<FormulaVariable, Long> {

    @Query(
        "SELECT new com.robinfood.taxes.domain.MappedVariable(ev.expression.id, ev.formulaVariable)"
            + " FROM ExpressionFormulaVariable ev"
            + " WHERE ev.expression.id IN :expressionIds"
            + " AND ev.deletedAt IS NULL AND ev.expression.deletedAt IS NULL")
    List<MappedVariable> findAllForExpressionIds(List<Long> expressionIds);

    @Query("SELECT ev.formulaVariable FROM ExpressionFormulaVariable ev "
        + "WHERE ev.expression.id = :expressionId "
        + "AND ev.deletedAt IS NULL and ev.expression.deletedAt IS NULL")
    List<FormulaVariable> findByExpressionId(Long expressionId);

    Page<FormulaVariable> deletedAtIsNull(Pageable pageable);

    Boolean existsByNameAndDeletedAtIsNull(String name);

    Boolean existsByIdAndDeletedAtIsNull(Long id);


}
