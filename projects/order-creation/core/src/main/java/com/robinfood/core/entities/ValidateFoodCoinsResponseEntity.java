package com.robinfood.core.entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ValidateFoodCoinsResponseEntity {

    private final int transactionCredits;

    private final boolean transactionStatus ;

    private final int userCurrentCredits;
}
