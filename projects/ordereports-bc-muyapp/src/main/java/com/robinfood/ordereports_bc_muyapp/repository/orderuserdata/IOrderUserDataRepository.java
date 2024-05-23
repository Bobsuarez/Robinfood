package com.robinfood.ordereports_bc_muyapp.repository.orderuserdata;

import com.robinfood.ordereports_bc_muyapp.models.entities.OrderUserDataEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Repository that handles user data information
 */
public interface IOrderUserDataRepository extends CrudRepository<OrderUserDataEntity, Long> {

    Optional<OrderUserDataEntity> findByOrderId(Integer orderId);


}
