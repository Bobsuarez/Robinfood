package com.robinfood.ordereports_bc_muyapp.repository.orders;

import com.robinfood.ordereports_bc_muyapp.models.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository that handles orders data
 */
@Repository
public interface IOrdersRepository extends JpaRepository<OrderEntity, Integer> {

    OrderEntity findByTransactionIdAndStatusIdNotIn(Integer transactionId, List<Long> status);
}
