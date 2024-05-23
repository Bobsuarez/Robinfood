package com.robinfood.taxes.repositories;

import com.robinfood.taxes.models.TaxType;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxTypeRepository extends SoftDeleteRepository<TaxType, Long> {

    boolean existsByNameAndCountryIdAndDeletedAtIsNull(String name, Long countryId);

    @Query("select t from TaxType t "
        + "where t.countryId = :countryId "
        + "and t.deletedAt is null "
        + "and (:status is null or t.status = :status)")
    List<TaxType> findByCountryId(
        @Param("countryId") Long countryId,
        @Param("status") Integer status);
}
