package com.robinfood.repository.orderfinalproductportions;

import com.robinfood.core.entities.OrderFinalProductPortionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository that handles order final product portion data
 */
public interface IOrderFinalProductPortionRepository extends CrudRepository<OrderFinalProductPortionEntity, Long> {

    List<OrderFinalProductPortionEntity> findOrderFinalProductPortionEntityByOrderFinalProductIdIn(
            List<Long> orderFinalProductIds
    );

    void deleteAllByOrderIdIsIn(List<Long> orderIds);

    List<OrderFinalProductPortionEntity> findOrderFinalProductPortionEntityByOrderId(Long orderId);

    List<OrderFinalProductPortionEntity> findOrderFinalProductPortionEntityByOrderFinalProductId(Long finalProductId);
}
