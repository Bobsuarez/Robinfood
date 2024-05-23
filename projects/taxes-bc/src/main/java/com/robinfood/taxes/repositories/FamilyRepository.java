package com.robinfood.taxes.repositories;

import com.robinfood.taxes.models.Family;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends SoftDeleteRepository<Family, Long> {

    @Query("SELECT f "
        + "FROM Family f "
        + "WHERE f.countryId = :countryId "
        + "AND f.familyType.id = :familyTypeId "
        + "AND (:status IS NULL OR f.status = :status) "
        + "AND f.deletedAt IS NULL")
    List<Family> findByCountryIdAndFamilyTypeIdAndStatus(
        Long countryId, Long familyTypeId, Integer status);

    Boolean existsByCountryIdAndNameAndDeletedAtIsNull(Long countryId, String name);

    Boolean existsByCountryIdAndNameAndDeletedAtIsNullAndIdNot(
        Long countryId, String name, Long familyId);

}
