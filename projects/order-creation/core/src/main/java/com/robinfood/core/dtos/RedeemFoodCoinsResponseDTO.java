package com.robinfood.core.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RedeemFoodCoinsResponseDTO implements Serializable {

    private final BigDecimal amount;

    private final Integer currentCredits;

    private final String uuid;
}
