package com.robinfood.core.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ValidateFoodCoinsRequestDTO  implements Serializable {
    private BigDecimal amount;
    private Long countryId;
    private int operationType;
    private Long userId;
}
