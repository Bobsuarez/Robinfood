package com.robinfood.repository.service;

import com.robinfood.core.entities.ServiceEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository that manages services
 */
public interface IServiceRepository extends CrudRepository<ServiceEntity, Long> {
}
