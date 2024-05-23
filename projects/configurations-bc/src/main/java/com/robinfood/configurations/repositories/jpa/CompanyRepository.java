package com.robinfood.configurations.repositories.jpa;

import com.robinfood.configurations.models.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends SoftDeleteRepository<Company, Long> {

    @Query("SELECT b FROM Company b"
            + " WHERE (:status is null or b.status = :status)"
    )
    List<Company> findAllByStatus(Long status);
}
