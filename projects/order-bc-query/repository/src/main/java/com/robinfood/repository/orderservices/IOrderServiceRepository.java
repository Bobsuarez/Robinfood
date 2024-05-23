package com.robinfood.repository.orderservices;

import com.robinfood.core.entities.OrderServicesEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IOrderServiceRepository extends CrudRepository<OrderServicesEntity, Long> {
    void deleteAllByOrderIdIsIn(List<Long> orderIds);

    List<OrderServicesEntity> findAllByOrderId(Long orderId);

    List<OrderServicesEntity> findAllByOrderIdIsIn(List<Long> orderIds);
}
