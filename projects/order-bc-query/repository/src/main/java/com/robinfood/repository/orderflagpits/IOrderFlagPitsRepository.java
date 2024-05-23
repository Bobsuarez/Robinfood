package com.robinfood.repository.orderflagpits;

import com.robinfood.core.entities.OrderFlagPitsEntity;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

/**
 * Repository that handles order flags pits data
 */
public interface IOrderFlagPitsRepository extends CrudRepository<OrderFlagPitsEntity, Long> {
    void deleteAllByOrderIdIsIn(List<Long> orderIds);
}
