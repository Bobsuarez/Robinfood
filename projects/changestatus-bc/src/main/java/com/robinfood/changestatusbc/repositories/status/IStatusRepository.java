package com.robinfood.changestatusbc.repositories.status;

import com.robinfood.changestatusbc.entities.StatusEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IStatusRepository extends CrudRepository<StatusEntity, Long> {
    
    Optional<StatusEntity> findByCode(String code);
}
