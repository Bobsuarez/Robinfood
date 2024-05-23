package com.robinfood.repository.orderexternaldiscount;

import com.robinfood.core.entities.OrderExternalDiscountEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository that handles order external discount data
 */
public interface IOrderExternalDiscountRepository extends CrudRepository<OrderExternalDiscountEntity, Long> {
    void deleteAllByOrderIdIsIn(List<Long> orderIds);
}
