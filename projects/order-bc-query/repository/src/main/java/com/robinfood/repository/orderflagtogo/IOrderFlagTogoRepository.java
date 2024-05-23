package com.robinfood.repository.orderflagtogo;

import com.robinfood.core.entities.OrderFlagTogoEntity;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

/**
 * Repository that handles order flags togo data
 */
public interface IOrderFlagTogoRepository extends CrudRepository<OrderFlagTogoEntity, Long> {
    void deleteAllByOrderIdIsIn(List<Long> orderIds);
}
