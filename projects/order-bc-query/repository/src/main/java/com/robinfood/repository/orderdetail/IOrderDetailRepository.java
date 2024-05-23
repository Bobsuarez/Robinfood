package com.robinfood.repository.orderdetail;

import com.robinfood.core.entities.OrderDetailEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository that handles order detail data
 */
public interface IOrderDetailRepository extends CrudRepository<OrderDetailEntity, Long> {

    List<OrderDetailEntity> findAllByOrderIdIn(List<Long> orderIds);

    void deleteAllByOrderIdIsIn(List<Long> orderIds);
}
