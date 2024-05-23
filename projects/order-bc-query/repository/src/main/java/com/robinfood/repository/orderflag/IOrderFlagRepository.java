package com.robinfood.repository.orderflag;

import com.robinfood.core.entities.OrderFlagEntity;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

/**
 * Repository that handles order flags data
 */
public interface IOrderFlagRepository extends CrudRepository<OrderFlagEntity, Long> {
    void deleteAllByOrderIdIsIn(List<Long> orderIds);
}
