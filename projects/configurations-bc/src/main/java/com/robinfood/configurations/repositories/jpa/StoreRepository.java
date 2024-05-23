package com.robinfood.configurations.repositories.jpa;

import com.robinfood.configurations.models.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends SoftDeleteRepository<Store, Long> {

    boolean existsByNameAndDeletedAtIsNull(String storeName);

    boolean existsByInternalNameAndDeletedAtIsNull(String internalName);

    @Query("SELECT m FROM  Store m  WHERE "
        + " m.deletedAt is null "
        + " AND (:name is null or m.name like '%'||:name||'%')"
        + " AND (:companyId is null or m.company.id = :companyId) ")
    Page<Store> findByNameAndCompanyCountryIdDeletedAtIsNull(@Param("name") String name,
                                                             @Param("companyId") Long companyId,
                                                             Pageable pageable);

    @Query("SELECT count(1) FROM  Store m  WHERE"
        + " m.deletedAt is null"
        + " AND (:name is null or m.name like '%'||:name||'%') "
        + " AND (:companyId is null or m.company.id = :companyId) ")
    int countByNameAndCompanyCountryIdDeletedAtIsNull(@Param("name") String name,
                                                      @Param("companyId") Long companyId);


    @Query(value = "SELECT stores.company_id FROM stores WHERE"
            + " stores.id = :storeId",
            nativeQuery = true)
    Integer getCompanyIdByStoreId(@Param("storeId") Long storeId);

    boolean existsByUuidAndDeletedAtIsNull(String uuid);

    List<Store> findByNameContains(String name);

}
