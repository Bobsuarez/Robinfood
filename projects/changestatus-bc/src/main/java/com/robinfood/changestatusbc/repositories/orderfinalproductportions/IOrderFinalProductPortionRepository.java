package com.robinfood.changestatusbc.repositories.orderfinalproductportions;

import com.robinfood.changestatusbc.entities.OrderFinalProductPortionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IOrderFinalProductPortionRepository extends CrudRepository<OrderFinalProductPortionEntity, Long> {

    List<OrderFinalProductPortionEntity> findOrderFinalProductPortionEntityByOrderId(Long orderId);
}
