package com.robinfood.repository.orderhistory;

import com.robinfood.core.entities.OrderHistoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository that manages order history data
 */
public interface IOrderHistoryRepository extends CrudRepository<OrderHistoryEntity, Long> {

    OrderHistoryEntity findByOrderIdAndOrderStatusId(Long orderId, Long statusId);
    void deleteAllByOrderIdIsIn(List<Long> orderIds);

    List<OrderHistoryEntity> findAllByOrderId(Long orderId);
}
