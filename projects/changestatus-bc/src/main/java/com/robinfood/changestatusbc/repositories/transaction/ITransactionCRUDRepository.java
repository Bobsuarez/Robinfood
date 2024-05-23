package com.robinfood.changestatusbc.repositories.transaction;

import com.robinfood.changestatusbc.entities.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface ITransactionCRUDRepository extends CrudRepository<TransactionEntity, Long> {

}
