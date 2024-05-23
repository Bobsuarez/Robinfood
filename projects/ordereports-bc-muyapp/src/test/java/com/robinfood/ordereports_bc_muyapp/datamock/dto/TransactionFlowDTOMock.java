package com.robinfood.ordereports_bc_muyapp.datamock.dto;

import com.robinfood.ordereports_bc_muyapp.dto.TransactionFlowDTO;

import java.time.LocalDateTime;

public class TransactionFlowDTOMock {

    public static TransactionFlowDTO getDataDefault() {
        return TransactionFlowDTO.builder()
                .transactionId(1L)
                .updatedAt(LocalDateTime.now())
                .flowId((short) 1)
                .id(1L)
                .createdAt(LocalDateTime.now())
                .build();

    }
}
