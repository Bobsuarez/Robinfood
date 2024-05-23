package com.robinfood.repository.orderremovedportions;

import com.robinfood.core.entities.FinalProductRemovedPortionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository that handles order removed portion data
 */
public interface IOrderRemovedPortionRepository extends CrudRepository<FinalProductRemovedPortionEntity, Long> {

    void deleteAllByOrderIdIsIn(List<Long> orderIds);

    List<FinalProductRemovedPortionEntity> findAllByOrderFinalProductIdIn(List<Long> finalProductIds);
}
