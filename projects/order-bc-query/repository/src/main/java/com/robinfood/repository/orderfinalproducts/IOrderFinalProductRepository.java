package com.robinfood.repository.orderfinalproducts;

import com.robinfood.core.entities.OrderFinalProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository that handles order final product data
 */
@Repository
public interface IOrderFinalProductRepository extends CrudRepository<OrderFinalProductEntity, Long> {

    List<OrderFinalProductEntity> findAllByOrderId(Long orderId);

    List<OrderFinalProductEntity> findAllByOrderIdIn(List<Long> orderIds);

    @Query("SELECT DISTINCT fp.brandId FROM OrderFinalProductEntity fp WHERE fp.orderId = ?1")
    List<Long> findAllDistinctBrandIdByOrderId(Long orderId);

    void deleteAllByOrderIdIsIn(List<Long> orderIds);

}
