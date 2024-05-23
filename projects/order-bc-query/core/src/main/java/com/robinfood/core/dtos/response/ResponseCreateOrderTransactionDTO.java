package com.robinfood.core.dtos.response;

import com.robinfood.core.dtos.response.transaction.ResponseCreatedOrderTransactionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseCreateOrderTransactionDTO {

    private final ResponseCreatedOrderTransactionDTO transaction;
}
