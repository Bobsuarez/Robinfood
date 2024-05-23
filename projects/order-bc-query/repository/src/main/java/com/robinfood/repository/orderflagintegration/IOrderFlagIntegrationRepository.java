package com.robinfood.repository.orderflagintegration;

import com.robinfood.core.entities.OrderFlagIntegrationEntity;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

/**
 * Repository that handles order flag integrations data
 */
public interface IOrderFlagIntegrationRepository extends CrudRepository<OrderFlagIntegrationEntity, Long> {
    void deleteAllByOrderIdIsIn(List<Long> orderIds);
}
