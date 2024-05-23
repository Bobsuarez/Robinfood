package com.robinfood.core.entities;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RedeemFoodCoinsResponseEntity {

    private final BigDecimal amount;

    private final Integer currentCredits;

    private final String uuid;
}
