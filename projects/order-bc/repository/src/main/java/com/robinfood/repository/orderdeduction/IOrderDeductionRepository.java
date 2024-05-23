package com.robinfood.repository.orderdeduction;

import com.robinfood.core.entities.OrderDeductionEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface IOrderDeductionRepository extends CrudRepository<OrderDeductionEntity, Long> {

    void deleteAllByOrderIdIsIn(List<Long> transactionsIds);

    List<OrderDeductionEntity> findAllByOrderIdIsIn(List<Long> ordersIds);
}
