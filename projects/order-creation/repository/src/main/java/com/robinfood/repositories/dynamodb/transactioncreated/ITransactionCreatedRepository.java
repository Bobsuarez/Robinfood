package com.robinfood.repositories.dynamodb.transactioncreated;

import com.robinfood.core.entities.template.TransactionCreatedEntity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface ITransactionCreatedRepository extends CrudRepository<TransactionCreatedEntity, String> {
}
