package com.robinfood.configurations.repositories.jpa;

import com.robinfood.configurations.constants.ConfigurationsBCConstants;
import com.robinfood.configurations.models.Pos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PosRepository extends SoftDeleteRepository<Pos, Long> {

    @Query("SELECT p.id FROM Pos p " +
            "WHERE p.store.id = :storeId " +
            "AND p.posType.id = :posTypeId " +
            "AND p.deletedAt = null " +
            "AND p.store.deletedAt = null " +
            "AND p.posType.deletedAt = null  " +
            "AND p.status = " + ConfigurationsBCConstants.STATUS_ACTIVE)
    Long findPosId(Long storeId, Long posTypeId);

    Optional<List<Pos>> findAllByStoreId(Long storesId);


    @Query("SELECT DISTINCT p FROM Pos p " +
            "LEFT JOIN p.resolutionList r " +
            "ON r.status = 1 " +
            "WHERE "
            + " p.deletedAt is null "
            + " AND ((:name IS NULL or r.name like '%'||:name||'%') "
            + " OR (:name IS NULL or p.store.name like '%'||:name||'%') "
            + " OR (:name IS NULL or p.name like '%'||:name||'%')) "
            + " AND (:status is null or p.status = :status)"
            + " AND (:storeId is null or p.store.id = :storeId) "
            + " ORDER BY "
            + " CASE WHEN r IS NOT NULL THEN 0 ELSE 1 END, "
            + " CASE WHEN r IS NOT NULL THEN r.endDate ELSE NULL END ASC"
    )
    Page<Pos> findByStoreIdAndNameAndStatus(
            @Param("name") String name, @Param("status") Long status, @Param("storeId") Long storeId, Pageable pageable
    );

}
