package com.robinfood.repository.transactionflow;

import com.robinfood.core.entities.TransactionFlowEntity;
import org.springframework.data.repository.CrudRepository;

public interface ITransactionFlowRepository extends CrudRepository<TransactionFlowEntity, Long> {

    void deleteTransactionFlowEntityByTransactionId(Long transactionId);

    TransactionFlowEntity findTransactionFlowEntityByTransactionId(Long transactionId);
}
