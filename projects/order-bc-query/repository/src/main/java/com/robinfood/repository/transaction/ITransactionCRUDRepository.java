package com.robinfood.repository.transaction;

import com.robinfood.core.entities.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository that handles transaction persistence data
 */
public interface ITransactionCRUDRepository extends CrudRepository<TransactionEntity, Long> {

    boolean existsTransactionEntityByUniqueIdentifier(String uuid);
}
