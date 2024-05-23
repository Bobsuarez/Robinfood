package com.robinfood.ordereports_bc_muyapp.repository.orderdevice;

import com.robinfood.ordereports_bc_muyapp.models.entities.OrderDeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository that handles order device data
 */
@Repository
public interface IOrderDeviceRepository extends JpaRepository<OrderDeviceEntity, Long> {

    OrderDeviceEntity findFirstByOrderIdOrderByIdDesc(Integer orderId);
}
