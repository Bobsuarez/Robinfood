package com.robinfood.configurations.repositories.jpa;

import com.robinfood.configurations.models.CompanyBrand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyBrandRepository extends SoftDeleteRepository<CompanyBrand, Long> {

    boolean existsByIdAndDeletedAtIsNull(Long id);

    Optional<CompanyBrand> findByBrandsIdAndCompanyId(Long brandId, Long companyId);

    @Query("SELECT cb FROM  CompanyBrand cb, StoreBrand sb  WHERE "
        + " cb.deletedAt is null "
        + " AND (:companyId is null or cb.company.id = :companyId) "
        + " AND (:name is null or cb.name like '%'||:name||'%')"
        + " AND (:storeId is null or sb.store.id = :storeId AND sb.brandCompany.id = cb.id)")
    Page<CompanyBrand> findByCompanyIdAndNameAndDeletedAtIsNull(Long companyId, String name,
        Long storeId, Pageable pageable);

    @Query(value = "SELECT * FROM  company_brands cb WHERE "
        + " cb.menu_id = :menuBrandId",
        nativeQuery = true)
    CompanyBrand findByCompanyIdAndMenuBrandId(@Param("menuBrandId") Long menuBrandId);

}
