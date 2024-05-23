package com.robinfood.taxes.repositories;

import com.robinfood.taxes.models.Tax;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxRepository extends SoftDeleteRepository<Tax, Long> {

    boolean existsByFamilyIdAndStatusAndDeletedAtIsNull(Long familyId, Integer status);

    boolean existsByTaxTypeIdAndDeletedAtIsNull(Long taxTypeId);

    boolean existsByExpressionIdAndDeletedAtIsNull(Long expressionId);

    @Query("SELECT t "
        + "FROM Tax t "
        + "WHERE t.family.id in :familyIds "
        + "AND t.status = :status "
        + "AND t.deletedAt IS NULL")
    List<Tax> findByFamilyInAndStatusAndDeleteIsNull(List<Long> familyIds, Integer status);

    List<Tax> findByFamilyIdAndDeletedAtIsNull(Long familyId);

    List<Tax> findByFamilyIdInAndStatusAndDeletedAtIsNull(List<Long> ids, Integer status);
}
