package com.robinfood.repository.orderproducttaxes;

import com.robinfood.core.entities.OrderProductTaxEntity;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

/**
 * Repository that manages order product taxes data
 */
public interface IOrderProductTaxesRepository extends CrudRepository<OrderProductTaxEntity, Long> {

    List<OrderProductTaxEntity> findByOrderFinalProductIdIn(List<Long> finalProductIds);
    void deleteAllByOrderIdIsIn(List<Long> orderIds);
}
