package com.robinfood.repository.orderflagsubmarine;

import com.robinfood.core.entities.OrderFlagSubmarineEntity;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

/**
 * Repository that handles order flags submarine data
 */
public interface IOrderFlagSubmarineRepository extends CrudRepository<OrderFlagSubmarineEntity, Long> {
    void deleteAllByOrderIdIsIn(List<Long> orderIds);
}
