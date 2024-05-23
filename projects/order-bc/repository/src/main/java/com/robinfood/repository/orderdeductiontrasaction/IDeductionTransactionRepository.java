package com.robinfood.repository.orderdeductiontrasaction;


import com.robinfood.core.entities.TrasanctionDeductionEntity;
import org.springframework.data.repository.CrudRepository;

public interface IDeductionTransactionRepository extends CrudRepository<TrasanctionDeductionEntity, Long> {
    void deleteAllByTrasactionId(Long transactionId);
}
