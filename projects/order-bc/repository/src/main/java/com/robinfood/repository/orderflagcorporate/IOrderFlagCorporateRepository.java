package com.robinfood.repository.orderflagcorporate;

import com.robinfood.core.entities.OrderFlagCorporateEntity;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

/**
 * Repository that handles order flags corporate data
 */
public interface IOrderFlagCorporateRepository extends CrudRepository<OrderFlagCorporateEntity, Long> {
    void deleteAllByOrderIdIsIn(List<Long> orderIds);
}
