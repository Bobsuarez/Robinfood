package com.robinfood.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionDTO {

    private Long id;

    private final Integer paid;

    private String uniqueIdentifier;

    private final Long userId;

    private final Double value;
}
