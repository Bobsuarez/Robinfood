package com.robinfood.ordereports_bc_muyapp.repository.service;

import com.robinfood.ordereports_bc_muyapp.models.entities.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository that manages services
 */
@Repository
public interface IServiceRepository extends JpaRepository<ServiceEntity, Long> {

}
