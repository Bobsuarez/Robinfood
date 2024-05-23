package com.robinfood.repository.orderproductodeduction;

import com.robinfood.core.entities.OrderDeductionFinalProductEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository that handles final product deductions
 */
@Repository
public interface IOrderProductFinalDeductionRepository extends CrudRepository<OrderDeductionFinalProductEntity, Long> {

    void deleteAllByOrderIdIsIn(List<Long> orderIds);

    List<OrderDeductionFinalProductEntity> findOrderDeductionFinalProductEntitiesByProductFinalIdIn(
            List<Long> orderFinalProductIds
    );
}
