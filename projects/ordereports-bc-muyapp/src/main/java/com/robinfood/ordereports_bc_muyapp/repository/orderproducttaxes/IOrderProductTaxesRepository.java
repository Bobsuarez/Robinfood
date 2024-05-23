package com.robinfood.ordereports_bc_muyapp.repository.orderproducttaxes;

import com.robinfood.ordereports_bc_muyapp.models.entities.OrderProductTaxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository that manages order product taxes data
 */
public interface IOrderProductTaxesRepository extends JpaRepository<OrderProductTaxEntity, Long> {

    Optional<List<OrderProductTaxEntity>> findByOrderFinalProductIdIn(List<Long> finalProductId);
}
