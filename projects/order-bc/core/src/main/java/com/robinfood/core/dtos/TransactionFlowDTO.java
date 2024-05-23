package com.robinfood.core.dtos;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionFlowDTO {

    private LocalDateTime createdAt;

    private Long id;

    private Long flowId;

    private Long transactionId;

    private LocalDateTime updatedAt;

}
