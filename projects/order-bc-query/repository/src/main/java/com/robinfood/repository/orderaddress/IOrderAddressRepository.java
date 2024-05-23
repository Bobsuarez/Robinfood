package com.robinfood.repository.orderaddress;

import com.robinfood.core.entities.OrderAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository that manages order address data
 */
public interface IOrderAddressRepository extends JpaRepository<OrderAddressEntity, Long> {
}
