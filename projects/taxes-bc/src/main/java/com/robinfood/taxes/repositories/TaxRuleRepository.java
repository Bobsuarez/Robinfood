package com.robinfood.taxes.repositories;

import com.robinfood.taxes.models.TaxRule;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxRuleRepository extends SoftDeleteRepository<TaxRule, Long> {

    List<TaxRule> findByTaxIdAndStatusAndDeletedAtIsNull(Long taxId, Integer status);

    boolean existsByTaxIdAndStatusAndDeletedAtIsNull(Long taxId, Integer status);

    @Query("SELECT CASE WHEN COUNT(tr) > 0 "
        + "THEN true ELSE false END "
        + "FROM TaxRule tr "
        + "WHERE (tr.leftVariable.id = :id OR tr.rightVariable.id = :id) "
        + "AND tr.deletedAt IS NULL")
    boolean existsByLeftVariableIdOrRightVariableId(Long id);

    boolean existsByTaxIdAndRuleTypeIdAndLeftVariableIdAndRightVariableIdAndDeletedAtIsNull(
        Long taxId, Long ruleTypeId, Long leftVariableId, Long rightVariableId);

    @Query("SELECT tr "
        + "FROM TaxRule tr "
        + "WHERE (:taxId IS NULL OR tr.tax.id = :taxId) "
        + "AND (:status IS NULL OR tr.status = :status) "
        + "AND tr.deletedAt IS NULL")
    Page<TaxRule> findByTaxIdAndStatusAndDeletedAtIsNull(Long taxId, Integer status,
        Pageable pageable);

}
