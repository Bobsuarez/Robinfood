package com.robinfood.ordereports_bc_muyapp.repository.ordercoupons;

import com.robinfood.ordereports_bc_muyapp.models.entities.OrderCouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IOrderCouponRepository extends JpaRepository<OrderCouponEntity, Long> {

    Optional<List<OrderCouponEntity>> findAllByTransactionId(Integer transactionId);

}
