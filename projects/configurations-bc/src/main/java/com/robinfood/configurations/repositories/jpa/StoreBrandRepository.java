package com.robinfood.configurations.repositories.jpa;

import com.robinfood.configurations.models.StoreBrand;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreBrandRepository  extends SoftDeleteRepository<StoreBrand, Long> {

    @Query(value = "SELECT COUNT(*) FROM store_brands WHERE"
            + " brand_company_id = :brandId AND store_id = :storeId",
    nativeQuery = true)
    int countByBrandIdAndStoreId (@Param("brandId") Long brandId, @Param("storeId") Long storeId);

}
