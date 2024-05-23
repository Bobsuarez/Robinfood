package com.robinfood.repository.orderpaymentdetail;

import com.robinfood.core.entities.OrderPaymentDetailEntity;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

/**
 * Repository that handles order statuses data
 */
public interface IOrderPaymentDetailRepository extends CrudRepository<OrderPaymentDetailEntity, Long> {
    void deleteAllByOrderIdIsIn(List<Long> orderIds);
}
