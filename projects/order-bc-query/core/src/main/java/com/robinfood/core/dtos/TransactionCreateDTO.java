package com.robinfood.core.dtos;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TransactionCreateDTO {

    private Long transactionId;

    private JsonNode requestTransaction;
}
