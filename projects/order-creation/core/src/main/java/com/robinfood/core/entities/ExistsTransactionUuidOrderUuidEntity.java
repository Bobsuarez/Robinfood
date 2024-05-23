package com.robinfood.core.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ExistsTransactionUuidOrderUuidEntity {

    private final boolean exits;

    private final String message;
}
