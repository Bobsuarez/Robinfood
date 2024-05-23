package com.robinfood.repository.origin;

import com.robinfood.core.entities.OriginEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository that handles origins data
 */
public interface IOriginRepository extends CrudRepository<OriginEntity, Long> {

}
