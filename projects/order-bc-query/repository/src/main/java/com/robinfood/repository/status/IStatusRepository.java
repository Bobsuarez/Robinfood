package com.robinfood.repository.status;

import com.robinfood.core.entities.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository that handles order statuses data
 */
public interface IStatusRepository extends JpaRepository<StatusEntity, Long> {

    List<StatusEntity> findAllByIdIn(List<Long> statusIds);
    
    Optional<StatusEntity> findByCode(String code);
}
