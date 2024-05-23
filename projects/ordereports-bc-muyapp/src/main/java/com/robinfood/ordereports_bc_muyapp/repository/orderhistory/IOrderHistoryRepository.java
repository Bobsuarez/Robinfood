package com.robinfood.ordereports_bc_muyapp.repository.orderhistory;

import com.robinfood.ordereports_bc_muyapp.models.entities.OrderHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository that manages order history data
 */
@Repository
public interface IOrderHistoryRepository extends JpaRepository<OrderHistoryEntity, Long> {

    List<OrderHistoryEntity> findAllByOrderId(Integer orderId);
}
