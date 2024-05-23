package com.robinfood.app.datamocks.dto.core;

import com.robinfood.core.dtos.TransactionFlowDTO;
import java.time.LocalDateTime;

public class TransactionFlowDTOMock {

    public static TransactionFlowDTO build() {
        return new TransactionFlowDTO(
            LocalDateTime.now(),
            1L,
            1L,
            1L,
            LocalDateTime.now()
        );
    }
}
