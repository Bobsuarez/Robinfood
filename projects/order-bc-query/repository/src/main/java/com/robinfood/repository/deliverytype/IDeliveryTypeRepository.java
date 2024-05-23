package com.robinfood.repository.deliverytype;

import com.robinfood.core.entities.DeliveryTypeEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository that manages order history data
 */
public interface IDeliveryTypeRepository extends CrudRepository<DeliveryTypeEntity, Long> {

    @NotNull
    @Override
    List<DeliveryTypeEntity> findAll();
}
