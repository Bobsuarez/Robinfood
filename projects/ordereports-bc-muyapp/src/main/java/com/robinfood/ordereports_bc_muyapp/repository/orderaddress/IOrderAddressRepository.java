package com.robinfood.ordereports_bc_muyapp.repository.orderaddress;

import com.robinfood.ordereports_bc_muyapp.models.entities.OrderAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository that manages order address data
 */
@Repository
public interface IOrderAddressRepository extends JpaRepository<OrderAddressEntity, Integer> {
}
