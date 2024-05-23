package com.robinfood.repository.typededuction;


import com.robinfood.core.entities.OrderTypeDeductionsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IOrderTypeDeductionRepository extends CrudRepository<OrderTypeDeductionsEntity, Long> {
    List<OrderTypeDeductionsEntity> findAllByStatus(Long id);
}
