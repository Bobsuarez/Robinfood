package com.robinfood.repository.ordercoupons;

import com.robinfood.core.entities.OrderCouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderCouponRepository extends JpaRepository<OrderCouponEntity, Long> {

    void deleteOrderCouponEntitiesByTransactionId(Long transactionId);

    List<OrderCouponEntity> findOrderCouponEntityByTransactionId(Long transactionId);

}
