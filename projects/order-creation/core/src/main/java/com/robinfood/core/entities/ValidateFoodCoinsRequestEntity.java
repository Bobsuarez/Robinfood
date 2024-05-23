package com.robinfood.core.entities;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class ValidateFoodCoinsRequestEntity {
    private BigDecimal amount;
    private Long countryId;
    private int operationType;
    private Long userId;
}
