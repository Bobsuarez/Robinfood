package com.robinfood.ordereports_bc_muyapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
public class TransactionFlowDTO {

    private LocalDateTime createdAt;

    private Long id;

    private Short flowId;

    private Long transactionId;

    private LocalDateTime updatedAt;

}
