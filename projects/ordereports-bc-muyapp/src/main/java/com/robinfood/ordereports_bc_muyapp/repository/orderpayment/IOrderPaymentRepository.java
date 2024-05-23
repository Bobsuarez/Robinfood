package com.robinfood.ordereports_bc_muyapp.repository.orderpayment;

import com.robinfood.ordereports_bc_muyapp.models.entities.OrderPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository that handles order payment data
 */
@Repository
public interface IOrderPaymentRepository extends JpaRepository<OrderPaymentEntity, Long> {

    List<OrderPaymentEntity> findAllByOrderId(Integer orderId);
}
