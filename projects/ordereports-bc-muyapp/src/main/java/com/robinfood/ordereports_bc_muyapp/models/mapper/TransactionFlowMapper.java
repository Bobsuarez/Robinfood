package com.robinfood.ordereports_bc_muyapp.models.mapper;

import com.robinfood.ordereports_bc_muyapp.dto.TransactionFlowDTO;
import com.robinfood.ordereports_bc_muyapp.models.entities.TransactionFlowEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionFlowMapper {

    public TransactionFlowDTO toTransactionFlowDTO(
            TransactionFlowEntity transactionFlowEntity
    ) {
        return TransactionFlowDTO.builder()
                .createdAt(transactionFlowEntity.getCreatedAt())
                .id(transactionFlowEntity.getId())
                .flowId(transactionFlowEntity.getFlowId())
                .transactionId(transactionFlowEntity.getTransactionId())
                .updatedAt(transactionFlowEntity.getUpdatedAt())
                .build();
    }

}
