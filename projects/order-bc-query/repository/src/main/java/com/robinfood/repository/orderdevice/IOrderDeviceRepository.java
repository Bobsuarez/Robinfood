package com.robinfood.repository.orderdevice;

import com.robinfood.core.entities.OrderDeviceEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository that handles order device data
 */
public interface IOrderDeviceRepository extends CrudRepository<OrderDeviceEntity, Long> {

    void deleteAllByOrderIdIsIn(List<Long> orderIds);

    Optional<OrderDeviceEntity> findFirstByOrderIdOrderByIdDesc(Long orderId);
}
