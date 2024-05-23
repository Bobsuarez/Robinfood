package com.robinfood.repository.orderchangedportions;

import com.robinfood.core.entities.OrderChangedPortionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository that handles order changed portion data
 */
public interface IOrderChangedPortionRepository extends CrudRepository<OrderChangedPortionEntity, Long> {

    void deleteAllByOrderIdIsIn(List<Long> orderId);

    List<OrderChangedPortionEntity> findByOrderFinalProductPortionIdIn(List<Long> finalProductPortionIds);
}
