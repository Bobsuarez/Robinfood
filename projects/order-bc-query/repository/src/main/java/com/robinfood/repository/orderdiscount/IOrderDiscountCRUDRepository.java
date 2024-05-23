package com.robinfood.repository.orderdiscount;

import com.robinfood.core.entities.OrderDiscountEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository that handles order discount data
 */
public interface IOrderDiscountCRUDRepository extends CrudRepository<OrderDiscountEntity, Long> {

    List<OrderDiscountEntity> findOrderDiscountEntitiesByOrderId(
        Long orderId
    );

    List<OrderDiscountEntity> findOrderDiscountEntitiesByOrderIdInAndOrderFinalProductIdIsNull(
        List<Long> orderIds
    );

    List<OrderDiscountEntity> findOrderDiscountEntitiesByOrderFinalProductIdIn(
        List<Long> orderFinalProductIds
    );

    void deleteAllByOrderIdIsIn(
        List<Long> orderIds
    );
}
