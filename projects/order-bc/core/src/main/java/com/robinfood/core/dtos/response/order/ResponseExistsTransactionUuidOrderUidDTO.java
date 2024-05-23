package com.robinfood.core.dtos.response.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ResponseExistsTransactionUuidOrderUidDTO {

    private final boolean exits;

    private final String message;
}
