package com.robinfood.configurations.repositories.jpa;

import com.robinfood.configurations.models.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BrandRepository extends SoftDeleteRepository<Brand, Long> {

    @Query("SELECT b FROM Brand b"
            + " WHERE (:enabled is null or b.enabled = :enabled)"
    )
    Page<Brand> findAllPaginated(
            Pageable pageable,
            Boolean enabled
    );

}
