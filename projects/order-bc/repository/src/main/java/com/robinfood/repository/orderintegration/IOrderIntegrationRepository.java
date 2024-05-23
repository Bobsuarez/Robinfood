package com.robinfood.repository.orderintegration;

import com.robinfood.core.entities.OrderIntegrationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

/**
 * Repository that handles order integration data
 */
@Repository
public interface IOrderIntegrationRepository extends CrudRepository<OrderIntegrationEntity, Long> {
    void deleteAllByOrderIdIsIn(List<Long> orderIds);

    Optional<OrderIntegrationEntity> findByIntegrationId(String integrationId);
}
