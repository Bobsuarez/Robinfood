package com.robinfood.repository.orderbrandhistory;

import com.robinfood.core.entities.OrderBrandHistoryEntity;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

/**
 * Repository that manages order brand history data
 */
public interface IOrderBrandHistoryRepository extends CrudRepository<OrderBrandHistoryEntity, Long> {

    List <OrderBrandHistoryEntity> findAllByOrderIdAndOrderStatusId(Long orderId, Long statusId);
    void deleteAllByOrderIdIsIn(List<Long> orderIds);
}
