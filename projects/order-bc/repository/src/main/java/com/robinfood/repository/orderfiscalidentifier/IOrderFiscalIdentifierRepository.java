package com.robinfood.repository.orderfiscalidentifier;

import com.robinfood.core.entities.OrderFiscalIdentifierEntity;
import org.springframework.data.repository.CrudRepository;

public interface IOrderFiscalIdentifierRepository extends CrudRepository<OrderFiscalIdentifierEntity, Long> {

    void deleteByTransactionId(Long transactionId);

    OrderFiscalIdentifierEntity findByTransactionId(Long transactionId);
}
