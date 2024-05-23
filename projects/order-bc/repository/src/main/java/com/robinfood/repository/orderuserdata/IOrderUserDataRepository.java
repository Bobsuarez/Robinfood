package com.robinfood.repository.orderuserdata;

import com.robinfood.core.entities.OrderUserDataEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository that handles orders data
 */
public interface IOrderUserDataRepository extends CrudRepository<OrderUserDataEntity, Long> {
    void deleteAllByOrderIdIsIn(List<Long> orderIds);
}
