package com.robinfood.ordereports_bc_muyapp.repository.status;

import com.robinfood.ordereports_bc_muyapp.models.entities.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository that handles order statuses data
 */
@Repository
public interface IStatusRepository extends JpaRepository<StatusEntity, Short> {

    List<StatusEntity> findByIdIn(List<Short> listIdsStatus);
}
