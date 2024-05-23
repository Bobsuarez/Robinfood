package com.robinfood.repository.orderfinalproducts;

import com.robinfood.core.entities.OrderFinalProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository that handles order final product data
 */
public interface IOrderFinalProductRepository extends CrudRepository<OrderFinalProductEntity, Long> {

    List<OrderFinalProductEntity> findAllByOrderId(Long orderId);

    List<OrderFinalProductEntity> findAllByOrderIdIn(List<Long> orderIds);

    @Query("SELECT DISTINCT fp.brandId FROM OrderFinalProductEntity fp WHERE fp.orderId = ?1")
    List<Long> findAllDistinctBrandIdByOrderId(Long orderId);

    void deleteAllByOrderIdIsIn(List<Long> orderIds);
}
