package com.robinfood.core.dtos.request.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestTransactionDTO {

    private Long id;

    private Long flowId;

    private final Boolean paid;

    private final Double value;

    private String uniqueIdentifier;

    private final Long userId;

}
