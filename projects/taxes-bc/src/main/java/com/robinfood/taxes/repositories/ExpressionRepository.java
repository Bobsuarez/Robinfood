package com.robinfood.taxes.repositories;

import com.robinfood.taxes.domain.FamilyExpression;
import com.robinfood.taxes.models.Expression;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpressionRepository extends SoftDeleteRepository<Expression, Long> {

    @Query("SELECT new com.robinfood.taxes.domain.FamilyExpression(t.id, t.expression)"
        + " FROM Tax t"
        + " WHERE t.id IN :taxIds")
    List<FamilyExpression> findAllByTaxId(List<Long> taxIds);

    Page<Expression> findAllByStatusAndDeletedAtIsNull(Integer status, Pageable pageable);

    Boolean existsByIdAndDeletedAtIsNull(Long id);

}
