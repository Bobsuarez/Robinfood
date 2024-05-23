package com.robinfood.ordereports_bc_muyapp.repository.orderfinalproductportions;

import com.robinfood.ordereports_bc_muyapp.models.entities.OrderFinalProductPortionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Repository that handles order final product portion data
 */
@Repository
public interface IOrderFinalProductPortionRepository extends JpaRepository<OrderFinalProductPortionEntity, Long> {

    CompletableFuture<List<OrderFinalProductPortionEntity>> findByOrderFinalProductIdIn(List<Long> finalProductIds);
}
