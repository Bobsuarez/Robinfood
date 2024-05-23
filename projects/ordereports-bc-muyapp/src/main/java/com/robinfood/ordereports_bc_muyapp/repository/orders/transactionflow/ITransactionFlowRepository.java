package com.robinfood.ordereports_bc_muyapp.repository.orders.transactionflow;

import com.robinfood.ordereports_bc_muyapp.models.entities.TransactionFlowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionFlowRepository extends JpaRepository<TransactionFlowEntity, Long> {

    TransactionFlowEntity findTransactionFlowEntityByTransactionId(Long transactionId);
}
