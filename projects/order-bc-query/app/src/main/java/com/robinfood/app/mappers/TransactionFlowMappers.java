package com.robinfood.app.mappers;

import com.robinfood.core.dtos.TransactionFlowDTO;
import com.robinfood.core.entities.TransactionFlowEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionFlowMappers {

    public TransactionFlowDTO toTransactionFlowDTO(
        TransactionFlowEntity transactionFlowEntity
    ) {
        return new TransactionFlowDTO(
            transactionFlowEntity.getCreatedAt(),
            transactionFlowEntity.getId(),
            transactionFlowEntity.getFlowId(),
            transactionFlowEntity.getTransactionId(),
            transactionFlowEntity.getUpdatedAt()
        );
    }

}
