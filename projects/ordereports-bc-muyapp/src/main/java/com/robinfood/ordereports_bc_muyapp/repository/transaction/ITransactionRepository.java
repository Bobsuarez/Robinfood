package com.robinfood.ordereports_bc_muyapp.repository.transaction;

import com.robinfood.ordereports_bc_muyapp.models.entities.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository that handles transaction persistence data
 */
public interface ITransactionRepository extends CrudRepository<TransactionEntity, Long> {

    TransactionEntity findByUniqueIdentifier(String transactionUuid);
}
