package com.robinfood.ordereports_bc_muyapp.repository.orderservices;

import com.robinfood.ordereports_bc_muyapp.models.entities.OrderServicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public interface IOrderServiceRepository extends JpaRepository<OrderServicesEntity, Long> {

    CompletableFuture<List<OrderServicesEntity>> findAllByOrderId(Integer orderId);
}
