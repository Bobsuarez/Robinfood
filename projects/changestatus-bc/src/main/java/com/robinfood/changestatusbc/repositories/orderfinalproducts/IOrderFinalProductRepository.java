package com.robinfood.changestatusbc.repositories.orderfinalproducts;

import com.robinfood.changestatusbc.entities.OrderFinalProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IOrderFinalProductRepository extends CrudRepository<OrderFinalProductEntity, Long> {

    @Query("SELECT DISTINCT fp.brandId FROM OrderFinalProductEntity fp WHERE fp.orderId = ?1")
    List<Long> findAllDistinctBrandIdByOrderId(Long orderId);
}
