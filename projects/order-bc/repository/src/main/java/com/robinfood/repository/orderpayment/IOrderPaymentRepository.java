package com.robinfood.repository.orderpayment;

import com.robinfood.core.entities.OrderPaymentEntity;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

/**
 * Repository that handles order payment data
 */
public interface IOrderPaymentRepository extends CrudRepository<OrderPaymentEntity, Long> {

    List<OrderPaymentEntity> findAllByOrderId(Long orderId);

    List<OrderPaymentEntity> findOrderPaymentEntitiesByOrderIdIn(List<Long> orderIds);

    void deleteAllByOrderIdIsIn(List<Long> orderIds);

}
