package com.robinfood.ordereports_bc_muyapp.repository.orderfinalproducts;

import com.robinfood.ordereports_bc_muyapp.models.entities.OrderFinalProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Repository that handles order final product data
 */
@Repository
public interface IOrderFinalProductRepository extends JpaRepository<OrderFinalProductEntity, Long> {

    CompletableFuture<List<OrderFinalProductEntity>> findAllByOrderId(Integer orderId);
}
