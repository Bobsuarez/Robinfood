package com.robinfood.repository.orderintegration;

import com.robinfood.core.entities.OrderIntegrationEntity;
import com.robinfood.core.entities.OrderUserDataEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository that handles order integration data
 */
@Repository
public interface IOrderIntegrationRepository extends CrudRepository<OrderIntegrationEntity, Long> {
    void deleteAllByOrderIdIsIn(List<Long> orderIds);

    Optional<OrderIntegrationEntity> findByIntegrationId(String integrationId);

    List<OrderIntegrationEntity> findAllByCreatedAtBetweenAndUserName(
            @Param("initialDateTime") LocalDateTime initialDateTime,
            @Param("finalDateTime") LocalDateTime finalDateTime,
            @Param("userName") String userName
    );

    Optional<OrderIntegrationEntity> findByOrderId(Long orderId);
}
