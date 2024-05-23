package com.robinfood.ordereports_bc_muyapp.repository.orderdiscount;

import com.robinfood.ordereports_bc_muyapp.models.entities.OrderDiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository that handles order discount data
 */
@Repository
public interface IOrderDiscountRepository extends JpaRepository<OrderDiscountEntity, Long> {

    List<OrderDiscountEntity> findOrderDiscountEntitiesByOrderId(
            Integer orderId
    );

    List<OrderDiscountEntity> findOrderDiscountEntitiesByOrderFinalProductIdIn(
            List<Long> orderFinalProductIds
    );
}
